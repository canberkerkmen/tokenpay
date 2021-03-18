package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostPaymentRequest {

    private String conversationId;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private BigDecimal walletPrice;
    private Integer installment;
    private Long buyerId;
    private PaymentEnum.Currency currency;
    private PaymentEnum.PaymentGroup paymentGroup;
    private PaymentEnum.PaymentPhase paymentPhase;
    private Card card;
    private String posAlias;
    private PaymentItem[] paymentItems;

}
