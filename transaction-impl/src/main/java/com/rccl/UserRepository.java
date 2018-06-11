package com.rccl;


import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletionStage;

public class UserRepository {
    
    public CompletionStage<User> getUser(int userId, JdbcSession session) {
        return session.withConnection( connection ->
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
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
    
    public CompletionStage<String> deleteUser(int userId, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("delete from txns.USER_DETAILS where user_id = ?")) {
                        callableStatement.setInt(1, userId);
                        callableStatement.execute();
                        return "user deleted successfully!!";
                    }
                
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
   /* public CompletionStage<String> addUser(User user, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("insert into txns.USER_DETAILS (?, ?, ?, ?, ?)")) {
                        callableStatement.setInt(1, user.getUserId);
                        callableStatement.setString(2, user.getName);
                        callableStatement.setString(3, user.getEmail);
                        callableStatement.setString(4, user.getUsername);
                        callableStatement.setString(5, user.getPhone);
                        callableStatement.execute();
                        return "user added successfully!!";
                    }
                
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }*/
    
    
    
}