package com.accountable.entity;

import com.accountable.enums.Role;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "users";
  public static final String IS_ACTIVE = "is_active";

  @Column(name = "user_id")
  @Id
  @NonNull
  private UUID id;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "username")
  @NonNull
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "country")
  private String country;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getGrantedAuthorities();
  }

  @Column(name = "school_id")
  private String schoolId;

  @Column(name = "classroom_id")
  private String classroomId;

  @Column(name = IS_ACTIVE)
  private Boolean isActive;
}
