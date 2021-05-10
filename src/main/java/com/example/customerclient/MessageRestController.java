package com.example.customerclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageRestController {

    private final RestTemplate restTemplate;

    MessageRestController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/message/{customerId}")
    String getMessage(@PathVariable("customerId") Long customerId) {
        Customer customer = this.restTemplate.getForObject("http://localhost:8000/customer/{customerId}", Customer.class, customerId);
        return "Hello " + customer.getFirstName();
    }
}