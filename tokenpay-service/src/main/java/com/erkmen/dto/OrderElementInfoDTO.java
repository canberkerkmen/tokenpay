package com.erkmen.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderElementInfoDTO {

    @NotBlank
    private String productCode;

    @NotNull
    private Double quantity;

}
