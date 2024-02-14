package com.accountable.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse<T> extends CustomResponse {
  T data;
  List<GenericErrorResponse> errors;
}
