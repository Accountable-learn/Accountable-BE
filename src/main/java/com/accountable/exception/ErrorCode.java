package com.accountable.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  QUESTION_ON_ADD(HttpStatus.BAD_REQUEST),
  USER_ON_ADD(HttpStatus.BAD_REQUEST);

  private final HttpStatus status;
}
