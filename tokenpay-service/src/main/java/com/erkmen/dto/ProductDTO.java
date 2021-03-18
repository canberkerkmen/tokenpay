package com.erkmen.dto;

import com.erkmen.domain.enums.QuantityType;
import lombok.Data;

@Data
public class ProductDTO {

    private String code;

    private String name;

    private String description;

    private QuantityType quantityType;

    private Double price;

}
