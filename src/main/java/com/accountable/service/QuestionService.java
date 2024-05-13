package com.accountable.service;

import com.accountable.entity.Question;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
  private final QuestionRepository questionRepository;

  /**
   * This method generate random questions.
   *
   * @param questionNum Number of questions to generate
   * @return a list of questions
   */
  public List<Question> getRandQuestions(int questionNum) {
    if (questionNum > 10) {
      questionNum = 10;
    }
    return questionRepository.getRandomQuestion(questionNum);
  }

  public Question create(Question question) {
    if (question.getQuestionText().isBlank()) {
      throw new GenericException(ErrorCode.QUESTION_ON_ADD, "Cannot have empty question");
    }

    //    question.setIsActive(Boolean.TRUE);
    return questionRepository.saveAndFlush(question);
  }

  public List<Question> bulkCreate(List<Question> questions) {
    List<Question> addedQuestions = new ArrayList<>();
    for (Question question : questions) {
      addedQuestions.add(create(question));
    }
    return addedQuestions;
  }
}
