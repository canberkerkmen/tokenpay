package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentRefund {

    private Long id;
    private LocalDate createdDate;
    private String refundStatus;
    private PaymentEnum.RefundDestinationType refundDestinationType;
    private BigDecimal price;
    private BigDecimal refundBankPrice;
    private BigDecimal refundWalletPrice;
    private String conversationId;
    private PaymentEnum.PaymentType paymentType;
    private TokenPayError error;

}
