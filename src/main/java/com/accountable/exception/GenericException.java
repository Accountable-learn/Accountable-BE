package com.accountable.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GenericException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String message;
  private final Throwable cause;

  public GenericException(ErrorCode errorCode) {
    this(errorCode, null);
  }

  public GenericException(ErrorCode errorCode, String message) {
    this(errorCode, message, null);
  }
}
