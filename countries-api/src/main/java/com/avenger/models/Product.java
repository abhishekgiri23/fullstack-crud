package com.avenger.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Product {
    
    private Long productId;
    private String productName;
    private BigDecimal price;

}
