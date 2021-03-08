package com.igorivkin.pactprovider.service;

import com.igorivkin.pactprovider.model.BankProduct;

public interface BankProductGetService {
    BankProduct findById(Long id);
}
