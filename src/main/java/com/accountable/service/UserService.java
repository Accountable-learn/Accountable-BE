package com.accountable.service;

import com.accountable.entity.User;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepo;


  // TODO: maybe use UserDto in the future to reduce number of fields exposed
  public User getUserById(UUID id) {
      return userRepo.findUserByUserIdAndIsActiveTrue(id);
  }



  public User create(User user) {
    // should only get called once every user
    if (null == getUserById(user.getUserId())) {
      return userRepo.saveAndFlush(user);
    } else {
      throw new GenericException(ErrorCode.USER_ON_ADD, "User already exists");
    }
  }

  public User update(User user) {
    User currentUser = getUserById(user.getUserId());
    if (null != currentUser) {
      return userRepo.saveAndFlush(currentUser);
    } else {
      throw new GenericException(ErrorCode.USER_ON_ADD, "Error while updating users");
    }
  }

  // currently not supporting deleting user
  public User delete() {
    return null;
  }
}
