package com.accountable.repositories;

import com.accountable.entity.Question;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
  //  @Query(nativeQuery = true, value = "SELECT e FROM YourEntity e ORDER BY RANDOM()")
  //  Question getRandomQuestion();

  //  @Query(nativeQuery = true, value = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1")
  //  Question getRandomQuestion();
}
