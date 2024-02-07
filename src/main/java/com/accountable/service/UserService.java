package com.accountable.service;

import com.accountable.dto.UserDto;
import com.accountable.entity.Question;
import com.accountable.entity.User;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepo;

  public User getUserById(UUID id){
    return userRepo.findUserByIdAndIsActiveTrue(id);
  }


  // should only get called once every user
  public UserDto create(UserDto userDto) {
    if (null != getUserById(userDto.getUserId())){
      User newUser = new User();
      newUser.setId(userDto.getUserId());
      newUser.setEmail(userDto.getEmail());
      newUser.setUsername(userDto.getUsername());
      userRepo.saveAndFlush(newUser);
      return userDto;
    }else{
      throw new GenericException(ErrorCode.USER_ON_ADD, "User already exists");
    }
  }

  public User update(User user){
    User currentUser = getUserById(user.getId());
    if (null!=currentUser){
      return userRepo.saveAndFlush(currentUser);
    }else{
      throw new GenericException(ErrorCode.USER_ON_ADD, "Error while updating users");
    }
  }


  // currently not supporting deleting user
  public User delete(){
    return null;
  }
}
