package com.accountable.service;

import com.accountable.entity.Classroom;
import com.accountable.entity.User;
import com.accountable.entity.UserClassMapping;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.ClassroomRepository;
import com.accountable.repository.UserClassRepository;
import com.accountable.repository.UserRepository;
import com.accountable.utilities.EntityUtils;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassroomService {
  private final ClassroomRepository classroomRepo;
  private final UserRepository userRepo;
  private final UserClassRepository userClassRepo;

  public Classroom create(UUID orgId, Classroom classroom) {
    if (null != orgId) {
      classroom.setOrgId(orgId);
    }
    Classroom newClass = classroomRepo.saveAndFlush(classroom);
    if (null == newClass.getCode()) {
      // Class code is the first 7 digits of UUID (which is not unique)
      newClass.setCode(newClass.getId().toString().substring(0, 7));
      classroomRepo.saveAndFlush(newClass);
    }
    return newClass;
  }

  /** Get all the useful info for a classroom * */
  public Classroom get(UUID classroomId) {
    try {
      return classroomRepo.getClassroomByIdAndIsActiveTrue(classroomId);
    } catch (Exception e) {
      log.info(String.valueOf(e));
      throw new GenericException(
          ErrorCode.CLASSROOM_ON_FETCH, "Error occurred when fetching class info");
    }
  }

  /** Get all the classrooms this user is in i.e. fetch all classrooms for a teacher. * */
  public List<Classroom> getClassroomsByUserId(UUID userId) {
    return classroomRepo.getClassroomsByUserId(userId);
  }

  /** Get all the users in this classroom * */
  public List<User> getUsersByClassroomId(UUID classroomId) {
    return userClassRepo.findAllByClassroomIdAndIsActiveTrue(classroomId).stream()
        .map(UserClassMapping::getUser) // Directly get User from mapping
        .collect(Collectors.toList());
  }

  public Classroom update(Classroom classroom) {
    UUID classroomId = classroom.getId();
    Classroom existingClassroom = get(classroomId);
    if (null == existingClassroom) {
      throw new GenericException(ErrorCode.CLASSROOM_ON_UPDATE, "classroom does not exist");
    }

    Classroom patchedOrganization = EntityUtils.mergeEntitiesDelta(existingClassroom, classroom);
    return classroomRepo.saveAndFlush(patchedOrganization);
  }

  // TODO: Consider make isActive false to preserve user data instead of hard delete
  public Classroom delete(UUID classroomId) {
    Classroom existingClassroom = get(classroomId);
    if (null == existingClassroom) {
      throw new GenericException(ErrorCode.CLASSROOM_ON_UPDATE, "classroom does not exist");
    }
    classroomRepo.delete(existingClassroom);
    return existingClassroom;
  }
}
