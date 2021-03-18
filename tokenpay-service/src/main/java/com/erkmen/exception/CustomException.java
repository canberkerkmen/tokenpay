package com.erkmen.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

//    @Override
//    public String getMessage() {
//        return message;
//    }

//    public HttpStatus getHttpStatus() {
//        return httpStatus;
//    }

}
