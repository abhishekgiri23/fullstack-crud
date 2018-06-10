package com.avenger.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Transaction {
    private Long userId;
    private Long productId;
    private Integer quantity;

}
