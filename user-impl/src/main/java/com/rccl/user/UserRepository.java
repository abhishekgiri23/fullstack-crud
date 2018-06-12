package com.rccl.user;


import com.lightbend.lagom.javadsl.api.transport.NotFound;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class UserRepository {
    
    public CompletionStage<Optional<User>> getUser(int userId, JdbcSession session) {
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("select * from txns.USER_DETAILS where user_id = ?")) {
                        callableStatement.setInt(1, userId);
                        callableStatement.execute();
    
                        User.UserBuilder user = User.builder();
                        ResultSet resultSet = callableStatement.getResultSet();
                        if (!resultSet.next()) {
                            return Optional.<User>empty();
                        } else {
                            /*resultSet.next();*/
                            System.out.println(resultSet);
                            user.userId(resultSet.getLong("user_id"));
                            user.name(resultSet.getString("name"));
                            user.username(resultSet.getString("username"));
                            user.phone(resultSet.getString("phone"));
                            user.email(resultSet.getString("email"));
                            return Optional.of(user.build());
                        }
                    }
                }
        );
    }
    
    
    public CompletionStage<User> deleteUser(int userId, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("delete from txns.USER_DETAILS where user_id = ?")) {
                        callableStatement.setInt(1, userId);
                        callableStatement.execute();
                        User.UserBuilder user = User.builder();
                        
                        return user.build();
                    }
                
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
    public CompletionStage<User> addUser(User user, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("insert into txns.USER_DETAILS " +
                            "(user_id, name, email, username, phone) values (?, ?, ?, ?, ?)")) {
                        
                        callableStatement.setLong(1, user.getUserId());
                        callableStatement.setString(2, user.getName());
                        callableStatement.setString(3, user.getEmail());
                        callableStatement.setString(4, user.getUsername());
                        callableStatement.setString(5, user.getPhone());
                        callableStatement.execute();
                        User.UserBuilder userObj = User.builder();
    
                        return userObj.build();
                    }
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
    public CompletionStage<String> updateUser(int userID, User user, JdbcSession session){
        return session.withConnection( connection ->
                {
                    try (CallableStatement callableStatement = connection.prepareCall("UPDATE txns.USER_DETAILS  SET email = ? where user_id = ?")) {
                        
                        callableStatement.setString(1, user.getEmail());
                        callableStatement.setLong(2, userID);
                        
                        callableStatement.execute();
                        return "user updated successfully!!";
                    }
                }
        ).exceptionally(throwable -> {
            throw new RuntimeException("something went wrong", throwable);
        });
    }
    
    
    
}