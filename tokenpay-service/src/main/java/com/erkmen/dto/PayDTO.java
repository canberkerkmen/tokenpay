package com.erkmen.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PayDTO {


    @NotBlank
    private String orderId;

    @NotBlank
    private String creditCardNumber;

}
