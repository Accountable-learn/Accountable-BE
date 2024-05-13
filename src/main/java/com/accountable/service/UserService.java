package com.accountable.service;

import com.accountable.entity.User;
import com.accountable.entity.UserClassMapping;
import com.accountable.enums.Role;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.UserClassRepository;
import com.accountable.repository.UserRepository;
import com.accountable.utilities.EntityUtils;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepo;
  private final UserClassRepository userClassRepository;

  // TODO: maybe use UserDto in the future to reduce number of fields exposed
  public User getUserById(UUID id) {
    return userRepo.findUserByUserIdAndIsActiveTrue(id);
  }

  public List<User> listUsersByRoleAndClassroomId(UUID id, Role role) {
    List<UserClassMapping> mappings =
        userClassRepository.findAllByClassroomIdAndIsApprovedFalseAndIsActiveTrue(id);

    return mappings.stream()
        .map(UserClassMapping::getUser)
        .filter(user -> user.getRole().equals(role))
        .collect(Collectors.toList());
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
    User patchedUser = EntityUtils.mergeEntitiesDelta(currentUser, user);
    if (null != currentUser) {
      return userRepo.saveAndFlush(patchedUser);
    } else {
      throw new GenericException(ErrorCode.USER_ON_ADD, "Error while updating users");
    }
  }

  // currently not supporting deleting user
  public User delete() {
    return null;
  }
}
