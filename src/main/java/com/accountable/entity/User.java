package com.accountable.entity;

import com.accountable.enums.Role;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME)
public class User{
  public static final String TABLE_NAME = "users";

  @Column(name = "user_id")
  @Id
  @NotNull
  private UUID userId;

  @Column(name = "org_id")
  private UUID orgId;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "username")
  @NotNull
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "country")
  private String country;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  // for jackson - permissions fields
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role == null ? Collections.emptyList() : role.getGrantedAuthorities();
  }
}
