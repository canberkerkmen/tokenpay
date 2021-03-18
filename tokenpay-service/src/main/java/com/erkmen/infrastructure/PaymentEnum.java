package com.erkmen.infrastructure;

public class PaymentEnum {

    public enum Currency {

        TRY, USD, EUR;

    }

    public enum PaymentStatus {

        CARD_PAYMENT, DEPOSIT_PAYMENT, WALLET_PAYMENT, CARD_AND_WALLET_PAYMENT, BANK_TRANSFER;

    }

    public enum PaymentType {

        CARD_PAYMENT, DEPOSIT_PAYMENT, WALLET_PAYMENT, CARD_AND_WALLET_PAYMENT, BANK_TRANSFER;

    }

    public enum CardType {

        CREDIT_CARD, DEBIT_CARD, PREPAID_CARD

    }

    public enum CardAssociation {

        VISA, AMEX, MASTER_CARD, TROY
    }

    public enum TransactionStatus {

        WAITING_FOR_APPROVAL, APPROVED, PAYOUT_STARTED
    }

    public enum RefundDestinationType {

        CARD, WALLET

    }

    public enum PaymentGroup {

        PRODUCT, LISTING_OR_SUBSCRIPTION
    }

    public enum PaymentPhase {

        AUTH
    }
}
