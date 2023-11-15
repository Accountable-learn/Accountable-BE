package com.accountable.services;

import com.accountable.entity.Question;
import com.accountable.repositories.QuestionRepository;
import java.io.InvalidObjectException;
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
    //    // TODO: Figure out how to handle exception with error code
    try {
      if (question.getQuestionText().isBlank()) {
        throw new InvalidObjectException("Question text cannot be empty");
      }
    } catch (Exception e) {
      log.info("Question text cannot be empty");
    }

    question.setIsActive(Boolean.TRUE);
    return questionRepository.saveAndFlush(question);
  }
}
