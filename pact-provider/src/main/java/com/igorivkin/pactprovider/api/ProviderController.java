package com.igorivkin.pactprovider.api;

import com.igorivkin.pactprovider.model.BankProduct;
import com.igorivkin.pactprovider.service.BankProductGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProviderController {

    private final BankProductGetService bankProductGetService;

    @Autowired
    public ProviderController(BankProductGetService bankProductGetService) {
        this.bankProductGetService = bankProductGetService;
    }

    @GetMapping("/bankproducts/{id}")
    public ResponseEntity<BankProduct> processFindById(@PathVariable Long id) {
        return Optional.ofNullable(bankProductGetService.findById(id))
                .map(bankProduct -> ResponseEntity.status(HttpStatus.OK).body(bankProduct))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
