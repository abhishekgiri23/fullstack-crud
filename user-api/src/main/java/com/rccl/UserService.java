package com.rccl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.rccl.models.User;

import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.transport.Method.DELETE;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;
import static com.lightbend.lagom.javadsl.api.transport.Method.POST;
import static com.lightbend.lagom.javadsl.api.transport.Method.PUT;

public interface UserService extends Service {
    
    
    ServiceCall<NotUsed, User> getUserByUserId(int userId);
    
    ServiceCall<NotUsed, String> deleteUser(int userId);
    
    ServiceCall<User, String> updateUser();
    
    ServiceCall<User, String> addUser();
    
    ServiceCall<NotUsed, String> getHealth();
    
    @Override
    default Descriptor descriptor() {
        return Service.named("user")
                .withCalls(
                        restCall(GET, "/api/user/:userID", this::getUserByUserId),
                        restCall(DELETE, "/api/delete/user/:user", this::deleteUser),
                        restCall(PUT, "/api/updateuser", this::updateUser),
                        restCall(POST, "/api/add/user", this::addUser),
                        restCall(GET, "/health", this::getHealth))
                .withAutoAcl(true);
    }
}
