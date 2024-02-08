package com.accountable.controller;

import com.accountable.entity.Question;
import com.accountable.response.AbstractResponse;
import com.accountable.response.CustomResponse;
import com.accountable.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class QuestionController extends AbstractResponse {
  // TODO: Create custom response to include data for FE, return a response entity (status code,
  // data...)  rather than just the object
  private final QuestionService questionService;

  @GetMapping(path = "question")
  public ResponseEntity<Question> getRandQuestion() {
    return ResponseEntity.ok(questionService.getRandQuestion());
  }

  @PostMapping(path = "question")
  public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
    return ResponseEntity.ok(questionService.create(question));
  }

  //  @PatchMapping(path = "/question/{id}")
  //  public Question createQuestion(@PathVariable UUID uuid) {
  //    return questionService.update(uuid);
  //  }

  // TODO: REMOVE THIS (only for testing purpose)
  @GetMapping(path = "question/testing")
  public ResponseEntity<CustomResponse> testingFunction() {
    return okResponseEntity("it is good", new Question("Hello", true));
  }
}
