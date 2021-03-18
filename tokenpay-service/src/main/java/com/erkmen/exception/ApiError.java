package com.erkmen.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.erkmen.domain.Constants.FORMATTER;

@Getter
@RequiredArgsConstructor
public class ApiError {

    private String localDateTimeFormatted = FORMATTER.format(LocalDateTime.now());

    @NonNull
    @JsonIgnore
    private HttpStatus status;

    @NonNull
    private String message;

    @NonNull
    private List<String> errors;

}