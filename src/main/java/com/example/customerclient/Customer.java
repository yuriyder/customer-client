package com.example.customerclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer {
    private Long id;
    private String firstName;
    private String lastName;
}
