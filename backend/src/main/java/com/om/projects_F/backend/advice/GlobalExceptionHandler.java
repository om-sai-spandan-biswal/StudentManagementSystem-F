package com.om.projects_F.backend.advice;

import com.om.projects_F.backend.exception.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ApiException apiException = ApiException.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorToResponse(apiException),apiException.getHttpStatus()) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<String> subErrors = exception
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList() ;

        ApiException apiException = ApiException.builder()
                .message("Input Validation Failed")
                .subError(subErrors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorToResponse(apiException),apiException.getHttpStatus()) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerException(Exception exception) {
        ApiException apiException = ApiException.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(errorToResponse(apiException),apiException.getHttpStatus()) ;
    }

    private ApiResponse<?> errorToResponse(ApiException apiException) {
        return new ApiResponse<>(apiException) ;
    }
}
