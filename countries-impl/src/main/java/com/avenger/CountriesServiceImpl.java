package com.avenger;

import akka.NotUsed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.jdbc.JdbcSession;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class CountriesServiceImpl implements CountriesService {
    
    private final JdbcSession session;
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private String filePath = "/home/abhishek/Documents/practice/countries.json";
    
    
    @Inject
    public CountriesServiceImpl(JdbcSession session) {
        this.session = session;
    }
    
    
    @Override
    public ServiceCall<NotUsed, Optional<CountryResponse>> getDetail(Optional<String> code,
                                                                     Optional<String> name) {

//        return request -> {
//            CompletionStage<Optional<Row>> country;
//            if (code.isPresent()) {
//                country = session.selectOne(GET_COUNTRY_BY_CODE, code.get());
//            } else {
//                country = session.selectOne(GET_COUNTRY_BY_NAME, name.get() + "%");
//            }
//            return country.thenApply(rows -> rows.map(row ->
//                    CountryResponse.builder()
//                            .capital(row.getString("capital"))
//                            .population(BigInteger.valueOf(row.getLong("population")))
//                            .phoneCode(row.getString("phone"))
//                            .numericCode(row.getInt("numeric"))
//                            .neighbours(row.getString("neighbours"))
//                            .language(row.getString("languages"))
//                            .currencyName(row.getString("currency_name"))
//                            .currencyCode(row.getString("currency_code"))
//                            .continent(row.getString("continent"))
//                            .countryCode(row.getString("alpha_3"))
//                            .neighbours(row.getString("neighbours"))
//                            .countryName(row.getString("name"))
//                            .build()
//            )).exceptionally(throwable -> {
//                Throwable cause = throwable.getCause();
//
//                if(cause instanceof NoHostAvailableException){
//                    throw new NotFound("Cassandra not available");
//                }
//                throw new NotFound("Generic Exception ...... ");
//            });
//        };
        return session.withConnection(request -> {
        
        });
    }
    
    @Override
    public ServiceCall<NotUsed, NotUsed> getPopulation(Optional<String> filter) {
        return null;
        
        //session.executeWrite()
    }
    
    @Override
    public ServiceCall<NotUsed, NotUsed> getCurrency() {
        return null;
    }
}
