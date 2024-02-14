package com.accountable.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = Question.TABLE_NAME)
public class Question extends AbstractUuidEntity {
  // TODO: Extends AbstractUuidEntity class
  public static final String TABLE_NAME = "questions";
  public static final String ID_COL_NAME = "question_uuid";
  public static final String QUESTION_TEXT = "question_text";
  public static final String IS_ACTIVE = "is_active";

  @Column(name = QUESTION_TEXT)
  private String questionText;

  @Column(name = IS_ACTIVE)
  private Boolean isActive;
}
