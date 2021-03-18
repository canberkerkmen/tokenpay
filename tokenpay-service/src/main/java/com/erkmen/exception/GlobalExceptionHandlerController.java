package com.erkmen.exception;

import com.erkmen.domain.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Bean
    public ErrorAttributes errorAttributes() {
//         Hide exception field in the return object
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
                log.error(String.valueOf(errorAttributes.get("trace")));
                log.error(String.valueOf(errorAttributes.get("exception")));
                errorAttributes.remove("exception");
                return errorAttributes;
            }
        };
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().stream().
                forEach(fieldError -> errors.add(fieldError.getField() + Constants.STRING_COLON + fieldError.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().stream().
                forEach(error -> errors.add(error.getObjectName() + Constants.STRING_COLON + error.getDefaultMessage()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
        log.error(ex.getMessage());
        ApiError apiError = new ApiError(ex.getHttpStatus(), ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(HttpServletResponse res) throws IOException {
        log.error("Access denied");
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "Access denied", new ArrayList<>());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), Arrays.asList(error));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleAll(Exception ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), new ArrayList<>());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
