package com.accountable.controller;


import com.accountable.entity.Classroom;
import com.accountable.entity.Organization;
import com.accountable.response.CustomResponse;
import com.accountable.service.ClassroomService;
import com.accountable.service.OrganizationService;
import com.accountable.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.accountable.entity.Question;
import com.accountable.response.AbstractResponse;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/org/{orgId}/classroom/")
public class ClassroomController extends AbstractResponse{
    private final ClassroomService classroomService;

    @PostMapping(path = "create")
    //  @PreAuthorize("hasAnyAuthority('teacher:create')")
    public ResponseEntity<CustomResponse> createClassroom(@PathVariable UUID orgId, @RequestBody Classroom classroom) {
        return okResponseEntity("Classroom created successfully", classroomService.create(orgId, classroom));
    }

    @GetMapping(path = "{classroomId}")
    //  @PreAuthorize("hasAnyAuthority('student:read')")
    public ResponseEntity<CustomResponse> getClassroomById(@PathVariable UUID classroomId) {
        return okResponseEntity("Question generate successfully", classroomService.get(classroomId));
    }

    @GetMapping(path = "user/{userId}")
    //  @PreAuthorize("hasAnyAuthority('student:read')")
    public ResponseEntity<CustomResponse> getClassroomByUserId(@PathVariable UUID userId) {
        return okResponseEntity("Question generate successfully", classroomService.get(userId));
    }

    @PostMapping(path = "update")
    //  @PreAuthorize("hasAnyAuthority('teacher:update')")
    public ResponseEntity<CustomResponse> updateClassroom(@RequestBody Classroom classroom) {
        return okResponseEntity("Questions created successfully", classroomService.update(classroom));
    }

    @DeleteMapping(path = "delete")
    //  @PreAuthorize("hasAnyAuthority('teacher:update')")
    public ResponseEntity<CustomResponse> deleteClassroomById(@PathVariable UUID classroomId) {
        return okResponseEntity("Classroom deleted successfully", classroomService.delete(classroomId));
    }
}
