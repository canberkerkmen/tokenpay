package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentCard {

    private PaymentEnum.CardType cardType;
    private PaymentEnum.CardAssociation cardAssociation;
    private String cardBrand;
    private String cardHolderName;
    private String binNumber;
    private String lastFourDigits;
    private Integer installments;
    private Boolean isThreeDS;
    private Integer mdStatus;
    private BigDecimal pfCommissionRateAmount;
    private BigDecimal merchantCommissionRate;
    private BigDecimal merchantCommissionRateAmount;
    private TokenPayError error;

}
