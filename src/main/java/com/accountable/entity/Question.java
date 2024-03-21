package com.accountable.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


/*
* One question must associate with one question bank
* */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = Question.TABLE_NAME)
// not extends AbstractUuidEntity because we don't need metadata
public class Question {
  public static final String TABLE_NAME = "questions";

  public Question(String questionText){
    this.questionText = questionText;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(
          name = "id",
          updatable = false,
          nullable = false,
          columnDefinition = "uuid default uuid_generate_v4()")
  private UUID id;

  @Column(name = "question_text")
  @NotNull
  private String questionText;

//  @ManyToOne
//  @JoinColumn(name = "question_bank_id", referencedColumnName = "id")
//  @JsonIgnore
//  @NotNull
//  private QuestionBank questionBank;
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "questions_answers_id")
//  @JsonIgnore
//  private QuestionsAnswers questionsAnswers;
}
