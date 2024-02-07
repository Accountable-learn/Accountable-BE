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
@Table(name = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "users";
  public static final String IS_ACTIVE = "is_active";

  @Column(name="user_id")
  @Id private UUID id;

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastname;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "country")
  private String country;

  // TODO: figure out how to handle roles, abilities, and org...
  @Column(name = "role")
  private String role;

  @Column(name = "school")
  private String school;

  @Column(name = "classroom")
  private String classroom;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  @Column(name = IS_ACTIVE)
  private Boolean isActive;
}
