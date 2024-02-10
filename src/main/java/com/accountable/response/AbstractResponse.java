package com.accountable.response;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Object FE will get with ok response { customResponse (metadata), UserResponse (content including
 * data and errors array) }
 */
public class AbstractResponse {
  public <T> UserResponse<T> getUserResponse(
      String message, HttpStatus status, T data, GenericErrorResponse... errors) {
    List<GenericErrorResponse> errorMessages =
        null == errors || errors.length == 0 ? Collections.emptyList() : cleanup(errors);
    UserResponse<T> userResponse = new UserResponse<>();
    userResponse.setTimestamp(OffsetDateTime.now());
    userResponse.setStatus(status.value());
    userResponse.setMessage(message);
    userResponse.setData(data);
    userResponse.setErrors(errorMessages);
    return userResponse;
  }

  private List<GenericErrorResponse> cleanup(GenericErrorResponse... errors) {
    List<GenericErrorResponse> errorMessages = Arrays.asList(errors);
    return errorMessages.parallelStream().filter(Objects::nonNull).collect(Collectors.toList());
  }

  private <T> UserResponse<T> response(String message, T data, GenericErrorResponse... errors) {
    return getUserResponse(message, HttpStatus.OK, data, errors);
  }

  public <T> UserResponse<T> okResponse(String message, T data, GenericErrorResponse... errors) {
    return response(message, data, errors);
  }

  public <T> ResponseEntity<CustomResponse> okResponseEntity(
      String message, T data, GenericErrorResponse... errors) {
    return new ResponseEntity<>(okResponse(message, data, errors), HttpStatus.OK);
  }
}
