package com.erkmen.infrastructure;

import com.erkmen.domain.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TokenPayServiceGatewayTest {

    @Autowired
    private TokenPayServiceGateway tokenPayServiceGateway;

    @Test
    public void createRandomStringSuccessfully() {
        assertNotNull(tokenPayServiceGateway.createRandomString(Constants.CODE_VALUE_OF_ZERO, Constants.CODE_VALUE_OF_Z, 9));
    }

    @Test
    public void buildSignatureWithoutBodyAndQueryStringSuccessfully() throws NoSuchAlgorithmException {
        String signature = tokenPayServiceGateway.buildSignature("onboarding/v1/sub-merchants/1",
                "",
                "Xa15Fp11T",
                "");

        assertThat(signature.toUpperCase(), is("L/F2ZAOH/AGXZPIULNHDGOFZ+D5JS1097UP6RH11VSO="));
    }

    @Test
    public void buildSignatureWithBodySuccessfully() throws NoSuchAlgorithmException {
        String signature = tokenPayServiceGateway.buildSignature("onboarding/v1/buyers",
                "",
                "Xa15Fp11T",
                "{\"email\":\"haluk.demir@example.com\",\"name\":\"Haluk\",\"surname\":\"Demir\",\"gsmNumber\":\"905551111111\",\"identityNumber\":\"11111111110\",\"buyerExternalId\":\"0ac49f08-f2a9-4326-a4d8-f6c1b01596fb\"}");

        System.out.println(signature);
        assertThat(signature.toUpperCase(), is("IRWQTISFBKCSM/NGZZ9XGN9PCTBXC0YSUJIBZMUZ9VS="));
    }
}
