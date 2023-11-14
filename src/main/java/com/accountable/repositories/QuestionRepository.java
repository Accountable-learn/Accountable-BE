package com.accountable.repositories;

import com.accountable.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
//
//@Repository
//public interface QuestionRepository extends JpaRepository<Question, UUID> {
//    @Query(
//            nativeQuery = true,
//            value ="SELECT e FROM YourEntity e ORDER BY RANDOM()")
//    Question getRandomQuestion();
//}
