package com.accountable.services;

import com.accountable.entity.Question;
// import com.accountable.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    // private final QuestionRepository questionRepository;

    public Question getRandQuestion() {
        // return questionRepository.getRandomQuestion();
        return null;
    }
}
