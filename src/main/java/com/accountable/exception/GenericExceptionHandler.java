package com.accountable.exception;

import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GenericErrorResponse> onCustomException(GenericException exception) {
    GenericErrorResponse error =
        new GenericErrorResponse(
            OffsetDateTime.now(), exception.getMessage(), exception.getErrorCode());
    return new ResponseEntity<>(error, exception.getErrorCode().getStatus());
  }
}
