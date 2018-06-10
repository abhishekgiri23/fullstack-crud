package com.avenger;

import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;

@Value
@Builder
public class CountryResponse {
    
    String countryCode;
    
    String capital;
    
    String continent;
    
    String currencyCode;
    
    String currencyName;
    
    String language;
    
    String countryName;
    
    String phoneCode;
    
    BigInteger population;
    
    Integer numericCode;
    
    String neighbours;
    
}
