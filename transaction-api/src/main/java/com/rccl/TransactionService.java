package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.transport.Method.DELETE;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;
import static com.lightbend.lagom.javadsl.api.transport.Method.POST;
import static com.lightbend.lagom.javadsl.api.transport.Method.PUT;

public interface TransactionService extends Service {
    
    
    ServiceCall<NotUsed, User> getUserByUserId(int userId);
    
    ServiceCall<NotUsed, String> deleteUser(int userId);
    
    ServiceCall<User, String> updateUser();
    
    ServiceCall<NotUsed, Product> getProductByProductId(int productId);
    
    ServiceCall<User, String> addUser();
    
    ServiceCall<Product, String> addProduct();
    
    ServiceCall<NotUsed, String> getHealth();
    
    
    
    ServiceCall<NotUsed, Transaction> getTxnByProdIdAndUserId(int productId, int userId);
    
    @Override
    default Descriptor descriptor() {
        return Service.named("transactions")
                .withCalls(
                        Service.restCall(GET, "/api/user/:userID", this::getUserByUserId),
                        Service.restCall(DELETE, "/api/delete/user/:user", this::deleteUser),
                        Service.restCall(PUT, "/api/updateuser", this::updateUser),
                        Service.restCall(GET, "/api/product/:productId", this::getProductByProductId),
                        Service.restCall(GET, "/api/txn/user/:userId/productId/:productId", this::getTxnByProdIdAndUserId),
                        Service.restCall(POST, "/api/add/user", this::addUser),
                        Service.restCall(GET, "/api/add/product", this::addProduct),
                        Service.restCall(GET, "/health", this::getHealth))
                .withAutoAcl(true);
    }
}
