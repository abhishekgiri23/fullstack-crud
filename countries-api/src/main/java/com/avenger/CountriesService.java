package com.avenger;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.Optional;

import static com.lightbend.lagom.javadsl.api.transport.Method.GET;

public interface CountriesService extends Service {
    
    
    ServiceCall<NotUsed, Optional<CountryResponse>> getDetail(Optional<String> code,
                                                              Optional<String> name);
    
    ServiceCall<NotUsed, NotUsed> getPopulation(Optional<String> filter);
    
    ServiceCall<NotUsed, NotUsed> getCurrency();
    
    @Override
    default Descriptor descriptor() {
        return Service.named("countries")
                .withCalls(
                        Service.restCall(GET, "/country?code&name", this::getDetail),
                        Service.restCall(GET, "/population?filter", this::getPopulation),
                        Service.restCall(GET, "/currency", this::getCurrency))
                .withAutoAcl(true);
        
    }
    
}
