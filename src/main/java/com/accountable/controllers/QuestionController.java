package com.accountable.controllers;

import com.accountable.entity.Question;
import com.accountable.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class QuestionController {
  private final QuestionService questionService;

  @GetMapping(path = "/question")
  public ResponseEntity<Question> getRandQuestion() {
    return ResponseEntity.ok(questionService.getRandQuestion());
  }

  @PostMapping(path = "/question")
  public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
    return ResponseEntity.ok(questionService.create(question));
  }

  //  @PatchMapping(path = "/question/{id}")
  //  public Question createQuestion(@PathVariable UUID uuid) {
  //    return questionService.update(uuid);
  //  }

  // TODO: REMOVE THIS (only for testing purpose)
  @GetMapping(path = "/question/testing")
  public String testingFunction() {
    return "This is a question for you!";
  }
}
