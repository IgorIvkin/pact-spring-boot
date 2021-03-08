package com.igorivkin.pactprovider.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankProduct {
    private Long id;
    private String title;
    private String customerName;
    private LocalDateTime createdDate;
}
