package com.accountable.exception;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericErrorResponse {
  private OffsetDateTime timestamp;
  private String message;
  private ErrorCode error;
}
