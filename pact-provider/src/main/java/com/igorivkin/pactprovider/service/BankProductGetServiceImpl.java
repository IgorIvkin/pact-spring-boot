package com.igorivkin.pactprovider.service;

import com.igorivkin.pactprovider.model.BankProduct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BankProductGetServiceImpl implements BankProductGetService {

    @Override
    public BankProduct findById(Long id) {
        if (id == 1) {
            return BankProduct
                    .builder()
                    .id(1L)
                    .createdDate(LocalDateTime.now())
                    .customerName("John Ivkin")
                    .title("Personal bank card")
                    .build();
        } else {
            return null;
        }
    }

}
