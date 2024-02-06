package com.accountable.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "users";
  public static final String ID_COL_NAME = "question_uuid";
  public static final String QUESTION_TEXT = "question_text";
  public static final String IS_ACTIVE = "is_active";

  @Id private UUID id;

  //    @Column(name = "first_name")
  private String firstname;

  //    @Column(name = "last_name")
  private String lastname;

  //    @Column(name = "display_name")
  private String displayName;

  //    @Column(name = "user_name")
  private String username;

  //    @Column(name = "email")
  private String email;

  //    @Column(name = "country")
  private String country;

  // TODO: figure out how to handle roles, abilities, and org...
  //    @Column(name = "role")
  private String role;

  //    @Column(name = "school")
  private String school;

  //    @Column(name = "classroom")
  private String classroom;

  //    @Column(name = "is_admin")
  private String isAdmin;

  @Column(name = IS_ACTIVE)
  private Boolean isActive;
}
