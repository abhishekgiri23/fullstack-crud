package com.avenger;

public class QueryConstant {
    
    public static final String GET_COUNTRY_BY_CODE = "select * from countries.country where alpha_2 = ? ";
    
    public static final String GET_COUNTRY_BY_NAME = "select * from countries.country where name LIKE  ? ";
    
    public static final String GET_COUNTRY = "select * from countries.country ;";
    
    
    
    
    
}
