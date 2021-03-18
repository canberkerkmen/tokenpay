package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payout {

    private BigDecimal paidPrice;
    private PaymentEnum.Currency currency;
    private BigDecimal merchantPayoutAmount;
    private BigDecimal subMerchantPayoutAmount;
    private BigDecimal pfCommissionRateAmount;
    private BigDecimal pfConversionRate;
    private BigDecimal pfConversionRateAmount;

}
