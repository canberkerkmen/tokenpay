package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GetPaymentResponse {

    private Long id;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String orderId;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private BigDecimal walletPrice;
    private PaymentEnum.PaymentType paymentType;
    private PaymentEnum.Currency currency;
    private PaymentEnum.PaymentStatus paymentStatus;
    private String conversationId;
    private PaymentCard paymentCard;
    private PaymentRefund[] paymentRefunds;
    private PaymentTransaction[] paymentTransactions;

}
