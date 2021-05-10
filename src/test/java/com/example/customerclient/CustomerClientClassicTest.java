package com.example.customerclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = CustomerClientApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 8081)
public class CustomerClientClassicTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerClient client;

    @Test
    public void client_should_return_customer() throws Exception {

        final Long givenCustomerId = 1L;

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/client/1"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(200)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBody(objectMapper.writeValueAsString(new Customer(1L, "Michael", "Jordan")))
                ));

        Customer actualCustomer = client.getCustomer(givenCustomerId);

        BDDAssertions.then(actualCustomer.getId()).isEqualTo(1L);
        BDDAssertions.then(actualCustomer.getFirstName()).isEqualTo("Michael");
        BDDAssertions.then(actualCustomer.getSurname()).isEqualTo("Jordan");
    }
}