package com.erkmen.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasketInfoDTO {

    @NotNull
    private String productCode;

    @NotNull
    private Double quantity;

}
