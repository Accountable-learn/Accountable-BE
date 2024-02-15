package com.accountable.exception;

import static com.accountable.exception.ErrorCode.SERVER_ERROR;

import com.accountable.response.GenericErrorResponse;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {
  @ExceptionHandler(GenericException.class)
  public ResponseEntity<GenericErrorResponse> onGenericException(GenericException exception) {
    GenericErrorResponse error =
        new GenericErrorResponse(
            OffsetDateTime.now(), exception.getMessage(), exception.getErrorCode());
    return new ResponseEntity<>(error, exception.getErrorCode().getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GenericErrorResponse> onCustomException(Exception exception) {
    GenericErrorResponse error =
        new GenericErrorResponse(
            OffsetDateTime.now(), "something unexpected happened", SERVER_ERROR);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
