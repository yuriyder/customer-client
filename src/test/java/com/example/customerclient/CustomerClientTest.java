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
//@AutoConfigureWireMock (port = 8081)
//@AutoConfigureJson
@AutoConfigureStubRunner (ids ="com.example:customer-service:+:8081", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CustomerClientTest {

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void get_customer_from_service_contract() throws Exception {
//        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
//            .willReturn(
//                    WireMock.aResponse()
//                        .withStatus(200)
//                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(jsonForCustomer(new Customer(1L, "Jane"), new Customer(2L, "Bob")))
//            ));
//


//    private String jsonForCustomer(Customer ... customers) throws Exception {
//        List<Customer> customerList = Arrays.asList(customers);
//        return objectMapper.writeValueAsString(customerList);
//    }

        ResponseEntity<Customer> personResponseEntity = restTemplate.getForEntity("http://localhost:8081/customer/1", Customer.class);

        BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
        BDDAssertions.then(personResponseEntity.getBody().getFirstName()).isEqualTo("Michael");
        BDDAssertions.then(personResponseEntity.getBody().getLastName()).isEqualTo("Jordan");
    }
}