package com.igorivkin.pactconsumer.client;

import com.igorivkin.pactconsumer.model.BankProduct;

public interface BankProductClient {
    BankProduct findById(Long id);
}
