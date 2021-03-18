package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentItem {

    private String name;
    private BigDecimal price;
    private String externalId;
    private Long subMerchantId;
    private BigDecimal subMerchantPrice;

}
