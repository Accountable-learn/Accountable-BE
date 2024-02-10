package com.accountable.response;

import com.accountable.exception.ErrorCode;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/***
 * Object FE will get if exception is raised
 * */
@Getter
@Setter
@AllArgsConstructor
public class GenericErrorResponse {
  private OffsetDateTime timestamp;
  private String message;
  private ErrorCode error;
}
