package com.rccl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;
import com.rccl.models.User;
import com.rccl.product.UserRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class UserServiceImpl implements UserService {
    
    private final JdbcSession session;
    private final UserRepository userRepository;
    
    @Inject
    public UserServiceImpl(JdbcSession session,
                           UserRepository userRepository) {
        this.session = session;
        this.userRepository = userRepository;
    }
    
    /**
     *
     * @param userId
     * @return
     */
    @Override
    public ServiceCall<NotUsed, User> getUserByUserId(int userId) {
        
        return req -> userRepository.getUser(userId, session);
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<User, String> updateUser() {
        
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<NotUsed, String> getHealth() {
        return req -> CompletableFuture.completedFuture("Service is up");
    }
    
    /**
     *
     * @return
     */
    @Override
    public ServiceCall<User, String> addUser() {
        return null;
    }
    
    /**
     *
     * @param userID
     * @return
     */
    @Override
    public ServiceCall<NotUsed, String> deleteUser(int userID) {
        return req -> userRepository.deleteUser(userID, session);
    }
    
    
}
    

