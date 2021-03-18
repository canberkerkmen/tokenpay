package com.erkmen.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("external-tokenpay-service")
@Getter
@Setter
public class TokenPayServiceGatewayConfiguration {

    @Value("url")
    public String url;

    @Value("api-key")
    public String apiKey;

    @Value("secret-key")
    public String secretKey;

    @Value("auth-version")
    public String authVersion;

}