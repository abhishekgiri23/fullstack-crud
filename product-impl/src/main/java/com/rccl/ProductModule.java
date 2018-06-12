package com.rccl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.rccl.loader.MysqlConnection;

public class ProductModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(ProductService.class, ProductServiceImpl.class);
        bind(MysqlConnection.class).asEagerSingleton();
    }
    
}
