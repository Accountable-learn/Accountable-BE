package com.accountable.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  QUESTION_ON_ADD(HttpStatus.BAD_REQUEST),
  USER_ON_ADD(HttpStatus.BAD_REQUEST),
  ORG_ON_UPDATE(HttpStatus.BAD_REQUEST),
  CLASSROOM_ON_UPDATE(HttpStatus.BAD_REQUEST),
  SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

  private final HttpStatus status;
}
