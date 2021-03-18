package com.erkmen.infrastructure;

import lombok.Data;

@Data
public class TokenPayError {

    private String errorCode;
    private String errorGroup;
    private String errorName;
    private String errorMessage;

}
