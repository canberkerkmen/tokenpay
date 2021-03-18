package com.erkmen.infrastructure;

import com.erkmen.exception.GatewayException;
import io.vavr.control.Either;

import java.security.NoSuchAlgorithmException;

public interface TokenPayService {

    Either<GatewayException, PostPaymentResponse> postPayment(PostPaymentRequest postPaymentRequest) throws NoSuchAlgorithmException;

    public Either<GatewayException, GetPaymentResponse> getPaymentById(String paymentId) throws NoSuchAlgorithmException;

}
