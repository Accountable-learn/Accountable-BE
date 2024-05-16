package com.accountable.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.*;

/**
 * Each question bank can be assigned to different classrooms. isPublic will determine if this
 * question bank is available to other teachers of the same org. Questions library:
 * getAllQuestionBanksAndIsPublicOrIsOwner *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = QuestionBank.TABLE_NAME)
public class QuestionBank extends AbstractUuidEntity {
  public static final String TABLE_NAME = "question_banks";

  @Column(name = "owner_id")
  private UUID ownerId;

  @Column(name = "name")
  private String name;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "quesetion_ids")
  private List<Question> questions;

  @Column(name = "is_public")
  private Boolean isPublic;

  // TODO: Add tags in the future for filtering
  //    @ElementCollection
  //    @CollectionTable(name = "question_bank_tags", joinColumns = @JoinColumn(name =
  // "question_bank_id"))
  //    @Column(name = "tag")
  //    private Set<String> tags = new HashSet<>();
}
