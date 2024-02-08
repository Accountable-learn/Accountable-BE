package com.accountable.controller;

import com.accountable.dto.UserDto;
import com.accountable.entity.User;
import com.accountable.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userService/")
public class UserController {

  UserService userService;

  @GetMapping(path = "user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable UUID id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PostMapping(path = "user")
  public ResponseEntity<UserDto> createQuestion(@RequestBody UserDto user) {
    return ResponseEntity.ok(userService.create(user));
  }

  @PatchMapping(path = "user/{id}")
  public ResponseEntity<User> patchUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.update(user));
  }
}
