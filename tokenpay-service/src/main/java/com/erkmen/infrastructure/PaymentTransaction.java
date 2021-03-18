package com.erkmen.infrastructure;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentTransaction {

    private Long id;
    private LocalDate createdDate;
    private String externalId;
    private PaymentEnum.TransactionStatus transactionStatus;
    private LocalDate transactionStatusDate;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private BigDecimal walletPrice;
    private BigDecimal merchantPayoutAmount;
    private Long subMerchantId;
    private String subMerchantName;
    private BigDecimal subMerchantPrice;
    private BigDecimal subMerchantPayoutRate;
    private BigDecimal subMerchantPayoutAmount;
    private Payout payout;
    private PaymentTransactionCard paymentTransactionCard;
    private PaymentTransactionRefund[] paymentTransactionRefunds;

}
