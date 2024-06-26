package com.accountable.controller;

import com.accountable.entity.Question;
import com.accountable.response.AbstractResponse;
import com.accountable.response.CustomResponse;
import com.accountable.service.QuestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/org/{orgId}/")
// @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
public class QuestionController extends AbstractResponse {
  private final QuestionService questionService;

  @PostMapping(path = "question/create")
  //    @PreAuthorize("hasAnyAuthority('teacher:create')")
  public ResponseEntity<CustomResponse> createQuestion(@RequestBody Question question) {
    return okResponseEntity("Question created successfully", questionService.create(question));
  }

  @GetMapping(path = "questionGen/{questionNum}")
  //    @PreAuthorize("hasAnyAuthority('student:read')")
  public ResponseEntity<CustomResponse> getRandQuestion(@PathVariable int questionNum) {
    return okResponseEntity(
        "Question generate successfully", questionService.getRandQuestions(questionNum));
  }

  @PatchMapping(path = "questions/bulk")
  //    @PreAuthorize("hasAnyAuthority('teacher:create')")
  public ResponseEntity<CustomResponse> createQuestions(@RequestBody List<Question> questions) {
    return okResponseEntity(
        "Questions created successfully", questionService.bulkCreate(questions));
  }

  // TODO: REMOVE THIS (only for testing purpose)
  @GetMapping(path = "question/testing")
  //  @PreAuthorize("hasAnyAuthority('student:read')")
  public ResponseEntity<CustomResponse> testingFunction() {
    return okResponseEntity("it is good", new Question("How are you"));
  }
}
