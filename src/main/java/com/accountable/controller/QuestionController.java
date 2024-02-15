package com.accountable.controller;

import com.accountable.entity.Question;
import com.accountable.response.AbstractResponse;
import com.accountable.response.CustomResponse;
import com.accountable.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
//@PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
public class QuestionController extends AbstractResponse {
  private final QuestionService questionService;

  //  @GetMapping(path = "question")
  //  @PreAuthorize("hasAnyAuthority('student:read')")
  //  public ResponseEntity<Question> getRandQuestion() {
  //    return ResponseEntity.ok(questionService.getRandQuestion());
  //  }
  //
  //  @PostMapping(path = "question")
  //  @PreAuthorize("hasAnyAuthority('teacher:create')")
  //  public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
  //    return ResponseEntity.ok(questionService.create(question));
  //  }

  // TODO: REMOVE THIS (only for testing purpose)
  @GetMapping(path = "question/testing")
//  @PreAuthorize("hasAnyAuthority('student:read')")
  public ResponseEntity<CustomResponse> testingFunction() {
    return okResponseEntity("it is good", new Question("Hello", true));
  }
}
