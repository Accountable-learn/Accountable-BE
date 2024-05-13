package com.accountable.controller;

import com.accountable.entity.Classroom;
import com.accountable.response.AbstractResponse;
import com.accountable.response.CustomResponse;
import com.accountable.service.ClassroomService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/org/{orgId}/classroom/")
public class ClassroomController extends AbstractResponse {
  private final ClassroomService classroomService;

  @PostMapping(path = "create")
  //  @PreAuthorize("hasAnyAuthority('teacher:create')")
  public ResponseEntity<CustomResponse> createClassroom(
      @PathVariable UUID orgId, @RequestBody Classroom classroom) {
    return okResponseEntity(
        "Classroom created successfully", classroomService.create(orgId, classroom));
  }

  @GetMapping(path = "{classroomId}")
  //  @PreAuthorize("hasAnyAuthority('student:read')")
  public ResponseEntity<CustomResponse> getClassroomById(@PathVariable UUID classroomId) {
    return okResponseEntity("Classrooms retrieved successfully", classroomService.get(classroomId));
  }

  /** Endpoint to fetch all users of a classroom */
  @GetMapping(path = "{classroomId}/users")
  //  @PreAuthorize("hasAnyAuthority('teacher:read')")
  public ResponseEntity<CustomResponse> getUsersbyClassroomId(@PathVariable UUID classroomId) {
    return okResponseEntity(
        "Users retrieved successfully", classroomService.getUsersByClassroomId(classroomId));
  }

  /** Endpoint to fetch all classrooms for a user such as a teacher's class view */
  @GetMapping(path = "user/{userId}")
  //  @PreAuthorize("hasAnyAuthority('teacher:read')")
  public ResponseEntity<CustomResponse> getClassroomsByUserId(@PathVariable UUID userId) {
    return okResponseEntity(
        "Classrooms retrieved successfully", classroomService.getClassroomsByUserId(userId));
  }

  @PostMapping(path = "update")
  //  @PreAuthorize("hasAnyAuthority('teacher:update')")
  public ResponseEntity<CustomResponse> updateClassroom(@RequestBody Classroom classroom) {
    return okResponseEntity("Questions created successfully", classroomService.update(classroom));
  }

  @DeleteMapping(path = "{classroomId}")
  //  @PreAuthorize("hasAnyAuthority('teacher:update')")
  public ResponseEntity<CustomResponse> deleteClassroomById(@PathVariable UUID classroomId) {
    return okResponseEntity("Classroom deleted successfully", classroomService.delete(classroomId));
  }
}
