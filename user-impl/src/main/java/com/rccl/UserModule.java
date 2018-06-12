package com.rccl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.rccl.loader.MysqlConnection;

public class UserModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(UserService.class, UserServiceImpl.class);
        bind(MysqlConnection.class).asEagerSingleton();
    }
    
}
