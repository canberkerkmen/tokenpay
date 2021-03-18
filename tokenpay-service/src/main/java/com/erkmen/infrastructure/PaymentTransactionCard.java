package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentTransactionCard {

    private BigDecimal merchantCommissionRate;
    private BigDecimal merchantCommissionRateAmount;
    private BigDecimal pfCommissionRateAmount;
}
