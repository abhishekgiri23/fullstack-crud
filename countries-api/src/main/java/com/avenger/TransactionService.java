package com.avenger;

import akka.NotUsed;
import com.avenger.models.Product;
import com.avenger.models.Transaction;
import com.avenger.models.User;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.transport.Method.GET;

public interface TransactionService extends Service {
    
    
    ServiceCall<NotUsed, User> getUserByUserId(Long userId);
    
    ServiceCall<NotUsed, Product> getProductByProductId(Long productId);
    
    ServiceCall<NotUsed, User> getUserByUsername(String username);
    
    ServiceCall<NotUsed, User> getUserByEmail(String email);
    
    ServiceCall<NotUsed, String> getHealth();
    
    
    ServiceCall<NotUsed, Transaction> getTxnByProdIdAndUserId(Long productId, Long userId);
    
    @Override
    default Descriptor descriptor() {
        return Service.named("transactions")
                .withCalls(
                        Service.restCall(GET, "/api/user/:UserID", this::getUserByUserId),
                        Service.restCall(GET, "/api/user/email/:email", this::getUserByEmail),
                        Service.restCall(GET, "/api/user/username/:username", this::getUserByUsername),
                        Service.restCall(GET, "/api/product/:productId", this::getProductByProductId),
                        Service.restCall(GET, "/api/txn/productId/:productId/user/userId", this::getTxnByProdIdAndUserId),
                        Service.restCall(GET, "/health", this::getHealth)
                .withAutoAcl(true));
    }
}
