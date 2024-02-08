package com.accountable.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse<T> extends CustomResponse {
    T data;
    List<GenericErrorResponse> errors;
}

