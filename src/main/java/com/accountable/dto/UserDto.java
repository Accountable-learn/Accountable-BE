package com.accountable.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
  // Fields we got from Cognito
  private UUID userId;
  private String username;
  private String email;

  private String firstname;
  private String lastname;
  private String displayName;
  private String country;
  private String role;
  private String school;
  private String classroom;
  private String isAdmin;
}

//  Json:
//  {
//     "userId": "cc4d35f8-f0e1-709c-27ff-1965c0e60a8f",
//     "username": "TonyTesting1"
//     "email": "tonythetester@gmail.com"
//   }
