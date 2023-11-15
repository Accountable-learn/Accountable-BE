package com.accountable.services;

import com.accountable.entity.Question;
import com.accountable.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public Question getRandQuestion() {
    // return questionRepository.getRandomQuestion();
    return null;
  }

  public Question create(Question question) {
    //    // TODO: Figure out how to handle exception with error code
    //    try {
    //      if (question.getQuestionText().isBlank()) {
    //        throw new InvalidObjectException("Question text cannot be empty");
    //      }
    //    } catch (Exception e) {
    //      log.info("Question text cannot be empty");
    //    }
    //
    //    question.setIsActive(Boolean.TRUE);
    //    // return questionRepository.saveAndFlush(question);
    return null;
  }
}
