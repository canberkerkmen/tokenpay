package com.erkmen.infrastructure;

import lombok.Data;

@Data
public class Card {

    private String cardHolderName;
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
    private String cvc;
    private Boolean storeCardAfterSuccessPayment;
    private String cardAlias;

}
