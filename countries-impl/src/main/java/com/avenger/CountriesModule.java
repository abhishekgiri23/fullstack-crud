package com.avenger;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class CountriesModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(CountriesService.class, CountriesServiceImpl.class);
    }
    
}
