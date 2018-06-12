package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.Optional;

import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.transport.Method.DELETE;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;
import static com.lightbend.lagom.javadsl.api.transport.Method.POST;
import static com.lightbend.lagom.javadsl.api.transport.Method.PUT;

public interface TransactionService extends Service {
    
    ServiceCall<NotUsed, String> getHealth();
    
    ServiceCall<NotUsed, Optional<Transaction>> getTxnByProdIdAndUserId(long userId, int productId);
    
    ServiceCall<NotUsed, String> deleteTxn(long userId, int productId);
    
    @Override
    default Descriptor descriptor() {
        return Service.named("transactions")
                .withCalls(
                        restCall(GET, "/api/txn/user/:userId/productId/:productId", this::getTxnByProdIdAndUserId),
                        restCall(DELETE, "/api/delete/txn/user/:userId/product/:productId", this::deleteTxn),
                        restCall(GET, "/health", this::getHealth))
                .withAutoAcl(true);
    }
}
