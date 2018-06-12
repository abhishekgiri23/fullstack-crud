package com.rccl.transaction;


import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.Transaction;
import com.rccl.models.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class TransactionRepository {
    
    public CompletionStage<Optional<Transaction>> getTransaction(long userId, int productID, JdbcSession session){
        
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("select * from txns.txn where user_id = ? and product_id = ?")) {
    
                        callableStatement.setLong(1, userId);
    
                        callableStatement.setInt(2, productID);
    
                        callableStatement.execute();
    
                        Transaction.TransactionBuilder transaction = Transaction.builder();
                        ResultSet resultSet = callableStatement.getResultSet();
                        if (!resultSet.next()) {
                            return Optional.<Transaction>empty();
                        } else {
                                transaction.userId(resultSet.getLong("user_id"));
                                transaction.productId(resultSet.getLong("product_id"));
                                transaction.quantity(resultSet.getInt("quantity"));
                            return Optional.of(transaction.build());
                        }
                            }
                                });
    }
    
    
    public CompletionStage<String> deleteTxn(long userId, int productId,  JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("delete from txns.txn where user_id = ? and product_id = ?")) {
                        callableStatement.setLong(1, userId);
                        callableStatement.setInt(2, productId);
                        callableStatement.execute();
                        return "product deleted successfully!!";
                    }
                    
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
}
