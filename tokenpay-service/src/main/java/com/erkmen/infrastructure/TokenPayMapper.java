package com.erkmen.infrastructure;

public class TokenPayMapper {

    public PostPaymentRequest buildPostPaymentRequest() {
        PostPaymentRequest postPaymentRequest = new PostPaymentRequest();
        postPaymentRequest.setCard(buildCard("cardAlias", "cardHolderName", "cardNumber", "cvc", "expireMonth", "expireYear", true));
        return postPaymentRequest;
    }

    public Card buildCard(String cardAlias, String cardHolderName, String cardNumber, String cvc, String expireMonth, String expireYear, Boolean storeCardAfterSuccessPayment) {
        Card card = new Card();
        card.setCardAlias(cardAlias);
        card.setCardHolderName(cardHolderName);
        card.setCardNumber(cardNumber);
        card.setCvc(cvc);
        card.setExpireMonth(expireMonth);
        card.setExpireYear(expireYear);
        card.setStoreCardAfterSuccessPayment(storeCardAfterSuccessPayment);
        return card;
    }
}
