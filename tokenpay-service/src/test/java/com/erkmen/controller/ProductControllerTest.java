package com.erkmen.controller;

import com.erkmen.dto.ProductInfoDTO;
import com.erkmen.service.ProductService;
import com.erkmen.service.UserService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Test
    public void testAddEmployeeSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/products/add";
        URI uri = new URI(baseUrl);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO(String.valueOf(new Random().nextInt(99999)), "productName1", "productDescription1", "Litre", 5d);

        String token = userService.signin("admin", "admin123");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<ProductInfoDTO> request = new HttpEntity<>(productInfoDTO, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

    }


}
