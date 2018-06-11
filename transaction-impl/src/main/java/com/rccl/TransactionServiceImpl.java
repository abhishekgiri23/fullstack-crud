package com.rccl;

import akka.NotUsed;
import com.rccl.models.Product;
import com.rccl.models.Transaction;
import com.rccl.models.User;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.product.ProductRepository;
import com.rccl.transaction.TransactionRepository;

import javax.inject.Inject;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public class TransactionServiceImpl implements TransactionService {
    
    private final JdbcSession session;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;
    
 /*   private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private String filePath = "/home/abhishek/Documents/practice/countries.json";*/
    
    
    @Inject
    public TransactionServiceImpl(JdbcSession session,
                                  UserRepository userRepository,
                                  ProductRepository productRepository,
                                  TransactionRepository transactionRepository) {
        this.session = session;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }
    
    
    @Override
    public ServiceCall<NotUsed, User> getUserByUserId(int userId) {
        
        return req -> userRepository.getUser(userId, session);
        
        
        /* session.withConnection(connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("select * from txns.USER_DETAILS where user_id = ?")) {
                        callableStatement.setInt(1, userId);
                        callableStatement.execute();
                        
                        User.UserBuilder user = User.builder();
                        ResultSet resultSet = callableStatement.getResultSet();
                        resultSet.next();
                        user.userId(resultSet.getLong("user_id"));
                        user.name(resultSet.getString("name"));
                        user.username(resultSet.getString("username"));
                        user.phone(resultSet.getString("phone"));
                        user.email(resultSet.getString("email"));
                        return user.build();
                    }
                    
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong",throwable);
        });*/
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
    public ServiceCall<NotUsed, Product> getProductByProductId(int productId) {
    
        return request -> productRepository.getProduct(productId, session);
    
    }
    
    @Override
    public ServiceCall<NotUsed, Transaction> getTxnByProdIdAndUserId(int productId, int userId) {
        return request -> transactionRepository.getTransaction(userId, productId, session);
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
    

