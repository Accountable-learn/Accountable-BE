package com.accountable.controllers;

import com.accountable.entity.Question;
import com.accountable.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping(path = "/question")
    public Question getRandQuestion() {
        return questionService.getRandQuestion();
    }
}
