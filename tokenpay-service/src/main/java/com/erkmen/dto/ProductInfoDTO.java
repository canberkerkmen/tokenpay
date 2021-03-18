package com.erkmen.dto;


import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductInfoDTO {

    @NotBlank
    @NonNull
    private String code;

    @NotBlank
    @NonNull
    private String name;

    @NotBlank
    @NonNull
    private String description;

    @NotBlank
    @NonNull
    private String quantityType;

    @NotNull
    @NonNull
    private Double price;
}
