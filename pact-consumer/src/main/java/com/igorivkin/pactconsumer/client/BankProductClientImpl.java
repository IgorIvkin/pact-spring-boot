package com.igorivkin.pactconsumer.client;

import com.igorivkin.pactconsumer.model.BankProduct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BankProductClientImpl implements BankProductClient {

    private final RestTemplate restTemplate;

    public BankProductClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BankProduct findById(Long id) {
        return restTemplate.getForObject("/bankproducts/" + id, BankProduct.class);
    }
}
