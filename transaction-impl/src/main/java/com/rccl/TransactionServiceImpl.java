package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class TransactionServiceImpl implements TransactionService {
    
    private final JdbcSession session;
    
 /*   private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private String filePath = "/home/abhishek/Documents/practice/countries.json";*/
    
    
    @Inject
    public TransactionServiceImpl(JdbcSession session) {
        this.session = session;
    }
    
    
    @Override
    public ServiceCall<NotUsed, User> getUserByUserId(Long userId) {
    
        return null;
    }
    
    @Override
    public ServiceCall<NotUsed, User> getUserByEmail(String email) {
        
        return null;
    }
    
    @Override
    public ServiceCall<NotUsed, User> getUserByUsername(String username) {
        return null;
        
    }
    
    @Override
    public ServiceCall<NotUsed, Product> getProductByProductId(Long productId) {
        return null;
    }
    
    @Override
    public ServiceCall<NotUsed, Transaction> getTxnByProdIdAndUserId(Long productId, Long userId) {
        return null;
    }
    
    @Override
    public ServiceCall<NotUsed, String> getHealth() {
        return req -> CompletableFuture.completedFuture("Service is up");
    }
    
    @Override
    public ServiceCall<User, String> addUser() {
        return req -> CompletableFuture.completedFuture("User Added");
    }
    
    @Override
    public ServiceCall<Product, String> addProduct() {
        return req -> CompletableFuture.completedFuture("Product Added");
    }
    
    
}
    

