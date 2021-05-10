package com.example.customerclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class CustomerClient {

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer getCustomer(Long id) {
        ParameterizedTypeReference<Customer> cmr = new ParameterizedTypeReference<Customer>() {};

        ResponseEntity<Customer> responseEntity = restTemplate.exchange("http://localhost:8081/customer/" + id, HttpMethod.GET, null, cmr);
        return responseEntity.getBody();
    }
}

