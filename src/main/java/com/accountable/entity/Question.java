package com.accountable.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = Question.TABLE_NAME)
public class Question {
  // TODO: Extends AbstractUuidEntity class
  public static final String TABLE_NAME = "questions";
  public static final String ID_COL_NAME = "question_uuid";
  public static final String QUESTION_TEXT = "question_text";
  public static final String IS_ACTIVE = "is_active";

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(
      name = Question.ID_COL_NAME,
      updatable = false,
      nullable = false,
      columnDefinition = "uuid default uuid_generate_v4()")
  private UUID id;

  @Column(name = QUESTION_TEXT)
  private String questionText;

  @Column(name = IS_ACTIVE)
  private Boolean isActive;
}
