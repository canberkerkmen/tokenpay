package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PostPaymentResponse {

    private String conversationId;
    private Long id;
    private LocalDate createdDate;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private BigDecimal walletPrice;
    private String currency;
    private Long buyerId;
    private Integer installment;
    private PaymentEnum.PaymentType paymentType;
    private PaymentEnum.PaymentGroup paymentGroup;
    private PaymentEnum.PaymentStatus paymentStatus;
    private PaymentEnum.PaymentPhase paymentPhase;
    private Boolean isThreeDS;
    private BigDecimal merchantCommissionRate;
    private BigDecimal merchantCommissionRateAmount;
    private BigDecimal pfCommissionRateAmount;
    private String cardUserKey;
    private String cardToken;
    private Boolean paidWithStoredCard;
    private String binNumber;
    private String lastFourDigits;
    private PaymentEnum.CardType cardType;
    private PaymentEnum.CardAssociation cardAssociation;
    private String cardBrand;
    private PaymentTransaction[] paymentTransactions;

}
