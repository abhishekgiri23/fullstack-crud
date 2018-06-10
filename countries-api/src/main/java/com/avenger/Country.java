package com.avenger;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Country {
    
    String alpha_2;
    String alpha_3;
    Integer area;
    String capital;
    String continent;
    String currency_code;
    String currency_name;
    String eqivalent_fips_code;
    String fips;
    Integer geoname_id;
    String languages;
    String name;
    String neighbours;
    Integer numeric;
    String phone;
    Integer population;
    String postal_code_format;
    String postal_code_regex;
    String tld;
    
}
