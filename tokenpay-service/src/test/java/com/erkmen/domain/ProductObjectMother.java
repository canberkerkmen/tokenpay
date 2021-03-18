package com.erkmen.domain;

import com.erkmen.domain.enums.QuantityType;

import java.util.Arrays;
import java.util.List;

public class ProductObjectMother {

    public static List<Product> getProductList() {
        Product prod1 = createProduct("00011", "productName1", "productDescription1", QuantityType.LITRE, 15d);
        Product prod2 = createProduct("00012", "productName2", "productDescription2", QuantityType.PIECE, 20d);
        Product prod3 = createProduct("00013", "productName2", "productDescription3", QuantityType.PACKET, 25d);
        return Arrays.asList(prod1, prod2, prod3);
    }

    private static Product createProduct(String code, String description, String name, QuantityType quantityType, Double price) {
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setName(name);
        product.setQuantityType(quantityType);
        product.setPrice(price);
        product.setIsActive(Boolean.TRUE);
        return product;
    }
}
