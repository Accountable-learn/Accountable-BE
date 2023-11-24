package com.accountable.service;

import com.accountable.entity.Question;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
  private final QuestionRepository questionRepository;

  public Question getRandQuestion() {
    return questionRepository.getRandomQuestion();
  }

  public Question create(Question question) {
    if (question.getQuestionText().isBlank()) {
      throw new GenericException(ErrorCode.QUESTION_ON_ADD, "Cannot have empty question");
    }

    question.setIsActive(Boolean.TRUE);
    return questionRepository.saveAndFlush(question);
  }
}
