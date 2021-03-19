package com.erkmen.infrastructure;

import com.erkmen.domain.Constants;
import com.erkmen.exception.GatewayException;
import com.google.gson.Gson;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
@Slf4j
public class TokenPayServiceGateway implements TokenPayService {

    private final RestTemplate restTemplate;
    private final TokenPayServiceGatewayConfiguration tokenPayServiceGatewayConfiguration;
    private final Gson gson;

    @Override
    public Either<GatewayException, GetPaymentResponse> getPaymentById(String paymentId) throws NoSuchAlgorithmException {
        HttpEntity<String> entity = new HttpEntity<>(Constants.STRING_EMPTY, buildHeaders(getPaymentUrl(paymentId), Constants.STRING_EMPTY));

        return CompletableFuture
                .supplyAsync(() -> restTemplate.exchange(getPaymentUrl(paymentId), HttpMethod.GET, entity, GetPaymentResponse.class))
                .<Either<GatewayException, GetPaymentResponse>>thenApply(resp -> {
                    log.info("application service called");
                    return Either.right(resp.getBody());
                })
                .exceptionally(ex -> Either.left(new GatewayException(TokenPayServiceGateway.class.getName(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .join();
    }

    @Override
    public Either<GatewayException, PostPaymentResponse> postPayment(PostPaymentRequest postPaymentRequest) throws NoSuchAlgorithmException {

        HttpEntity<PostPaymentRequest> entity = new HttpEntity<>(postPaymentRequest, buildHeaders(postPaymentUrl(), gson.toJson(postPaymentRequest)));

        return CompletableFuture
                .supplyAsync(() -> restTemplate.exchange(postPaymentUrl(), HttpMethod.POST, entity, PostPaymentResponse.class))
                .<Either<GatewayException, PostPaymentResponse>>thenApply(resp -> {
                    log.info("external tokenpay service called");
                    return Either.right(resp.getBody());
                })
                .exceptionally(ex -> Either.left(new GatewayException(TokenPayServiceGateway.class.getName(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .join();
    }

    private HttpHeaders buildHeaders(String url, String body) throws NoSuchAlgorithmException {
        String randomString = createRandomString(Constants.CODE_VALUE_OF_ZERO, Constants.CODE_VALUE_OF_Z, 9);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", tokenPayServiceGatewayConfiguration.getApiKey());
        headers.set("x-rnd-key", randomString);
        headers.set("x-auth-version", tokenPayServiceGatewayConfiguration.getAuthVersion());
        headers.set("x-signature", buildSignature(url, "", randomString, body));
        return headers;
    }

    public String buildSignature(String url, String queryString, String randomString, String body) throws NoSuchAlgorithmException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tokenPayServiceGatewayConfiguration.getUrl() + url);
        stringBuilder.append(queryString);
        stringBuilder.append(tokenPayServiceGatewayConfiguration.getApiKey());
        stringBuilder.append(tokenPayServiceGatewayConfiguration.getSecretKey());
        stringBuilder.append(randomString);
        stringBuilder.append(body);
        return new String(Base64.getEncoder().encode(MessageDigest.getInstance("SHA-256").digest(stringBuilder.toString().getBytes(StandardCharsets.UTF_8))));
    }

    public String createRandomString(int startCodeValue, int endCodeValue, int randomStringLength) {
        return new Random().ints(startCodeValue, endCodeValue + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(randomStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String postPaymentUrl() {
        return tokenPayServiceGatewayConfiguration.getUrl() + "payment/v1/card-payments";
    }


    public String getPaymentUrl(String id) {
        return tokenPayServiceGatewayConfiguration.getUrl() + "payment-reporting/v1/payments/" + id;
    }

}
