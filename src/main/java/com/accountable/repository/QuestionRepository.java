package com.accountable.repository;

import com.accountable.entity.Question;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
  @Query(nativeQuery = true, value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT :questionNum")
  List<Question> getRandomQuestion(int questionNum);
}
