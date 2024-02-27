package com.accountable.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = Question.TABLE_NAME)
public class Question {
  public static final String TABLE_NAME = "questions";

  @Id
  @Column(name = "id")
  private UUID id;

  public Question (String questions){
    this.questionText = questions;
  }

  // each class will have all the org questions as default
  @Column(name = "school_id")
  private UUID orgId;

  @Column(name = "classroom_id")
  private UUID classroomId;

  @Column(name = "question_text")
  @NotNull
  private String questionText;
}
