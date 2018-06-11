package com.rccl.transaction;


import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.Transaction;
import com.rccl.models.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletionStage;

public class TransactionRepository {
    
    public CompletionStage<Transaction> getTransaction(int userId, int productID, JdbcSession session){
        
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("select * from txns.txn where user_id = ? and product_id = ? ")) {
                        
                        callableStatement.setInt(1, userId);
                        
                        callableStatement.setInt(2, productID);
                        
                        callableStatement.execute();
                    
                        Transaction.TransactionBuilder transaction = Transaction.builder();
                        ResultSet resultSet = callableStatement.getResultSet();
                        resultSet.next();
                        transaction.userId(resultSet.getLong("user_id"));
                        transaction.productId(resultSet.getLong("product_id"));
                        transaction.quantity(resultSet.getInt("quantity"));
                        return transaction.build();
                    }
                }
        ).exceptionally(throwable -> {
            throwable.printStackTrace();
            throw new RuntimeException("something went wrong", throwable);
        });
    
    
    
    }



}
