package com.accountable.repositories;

import com.accountable.entity.Question;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
  @Query(nativeQuery = true, value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1")
  Question getRandomQuestion();
}
