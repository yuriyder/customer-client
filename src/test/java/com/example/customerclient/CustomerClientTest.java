package com.example.customerclient;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = CustomerClientApplication.class)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        repositoryRoot = "git://git@bitbucket.org:iviadevelopment/service-contracts.git",
        ids = { "com.example:customer-service:0.0.1-SNAPSHOT:8081"})
public class CustomerClientTest {

    @Autowired
    private CustomerClient client;

    @Test
    public void get_customer_from_service_contract() {

        final Long givenCustomerId = 1L;

        Customer actualCustomer = client.getCustomer(givenCustomerId);

        BDDAssertions.then(actualCustomer.getId()).isEqualTo(1L);
        BDDAssertions.then(actualCustomer.getFirstName()).isEqualTo("Michael");
        BDDAssertions.then(actualCustomer.getLastName()).isEqualTo("Jordan");
    }
}