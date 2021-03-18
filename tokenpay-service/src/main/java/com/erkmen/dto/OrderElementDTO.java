package com.erkmen.dto;

import lombok.Data;

@Data
public class OrderElementDTO {

    private Double quantity;

    private Double totalPrice;

    private ProductDTO product;

}
