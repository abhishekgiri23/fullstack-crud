package com.avenger.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long userId;
    private String name;
    private String email;
    private String username;
    private String phone;
}
