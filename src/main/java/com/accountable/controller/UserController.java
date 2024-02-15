package com.accountable.controller;

import com.accountable.entity.User;
import com.accountable.response.AbstractResponse;
import com.accountable.response.CustomResponse;
import com.accountable.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userService/")
public class UserController extends AbstractResponse {

  private final UserService userService;

  @GetMapping(path = "user/{id}")
  // @PreAuthorize("hasAnyAuthority('student:read')")
  public ResponseEntity<CustomResponse> getUserById(@PathVariable UUID id) {
    return okResponseEntity("user retrieved successfully", userService.getUserById(id));
  }

  @PostMapping(path = "user")
  //  @PreAuthorize("hasAnyAuthority('student:write', 'teacher:write')")
  public ResponseEntity<CustomResponse> createUser(@RequestBody User user) {
    return okResponseEntity("user created successfully", userService.create(user));
  }

  @PatchMapping(path = "user")
  // @PreAuthorize("hasAnyAuthority('student:write', 'teacher:write')")
  public ResponseEntity<CustomResponse> patchUser(@RequestBody User user) {
    return okResponseEntity("user updated successfully", userService.update(user));
  }
}
