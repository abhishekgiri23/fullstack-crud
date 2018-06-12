package com.rccl;

import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;

import javax.inject.Inject;


public class MyCORSFilter extends DefaultHttpFilters {
    
    @Inject
    public MyCORSFilter(CORSFilter corsFilter) {
        super(corsFilter);
    }
}