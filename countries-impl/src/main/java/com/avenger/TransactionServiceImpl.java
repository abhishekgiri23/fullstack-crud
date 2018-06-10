package com.avenger;

import akka.NotUsed;
import com.avenger.models.Product;
import com.avenger.models.Transaction;
import com.avenger.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
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
}
    

