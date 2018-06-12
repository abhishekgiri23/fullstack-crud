package com.rccl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.rccl.loader.MysqlConnection;

public class TransactionModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(TransactionService.class, TransactionServiceImpl.class);
        bind(MysqlConnection.class).asEagerSingleton();
    }
    
}
