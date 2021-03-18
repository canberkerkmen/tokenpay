package com.erkmen.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CustomizedExceptionHandlerResponse {

    private String message;
    private String status;
    private LocalDateTime localDateTime;

}
