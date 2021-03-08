package com.igorivkin.pactconsumer;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.igorivkin.pactconsumer.client.BankProductClientImpl;
import com.igorivkin.pactconsumer.model.BankProduct;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "application.provider.base-url:http://localhost:8080"
)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "pact-provider", port = "8080")
public class PactConsumerTests {

    @Autowired
    private BankProductClientImpl bankProductClient;

    @Test
    @PactTestFor(pactMethod = "pactBankProductExists")
    public void bankProductClientSuccessTest() {
        BankProduct bankProduct = bankProductClient.findById(1L);
        assertEquals(bankProduct.getCustomerName(), "John Ivkin");
    }

    @Test
    @PactTestFor(pactMethod = "pactBankProductDoesNotExist")
    public void bankProductClientNotFoundTest() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            BankProduct bankProduct = bankProductClient.findById(2L);
        });
        if(exception.getMessage() != null) {
            assertTrue(exception.getMessage().contains("404"));
        }
    }

    @Pact(consumer = "pact-consumer")
    public RequestResponsePact pactBankProductExists(PactDslWithProvider pactDslWithProvider) {
        return pactDslWithProvider.given("Bank product with ID 1 exists")
                .uponReceiving("A request to /bankproducts/1")
                .path("/bankproducts/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(LambdaDsl.newJsonBody((object) ->
                        object.stringType("customerName", "John Ivkin")).build())
                .toPact();
    }

    @Pact(consumer = "pact-consumer")
    public RequestResponsePact pactBankProductDoesNotExist(PactDslWithProvider pactDslWithProvider) {
        return pactDslWithProvider.given("Bank product with ID 2 does not exist")
                .uponReceiving("A request to /bankproducts/2")
                .path("/bankproducts/2")
                .method("GET")
                .willRespondWith()
                .status(404)
                .body("")
                .toPact();
    }
}
