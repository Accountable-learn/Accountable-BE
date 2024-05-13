package com.accountable.entity;

import com.accountable.enums.Role;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

// TODO: Needs to add some counting metrics
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = User.TABLE_NAME)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {
  public static final String TABLE_NAME = "users";

  @Column(name = "user_id")
  @Id
  @NotNull
  private UUID userId;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
  @ToString.Exclude
  @JsonIgnore
  private Organization organization;

  // for jackson - permissions fields
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role == null ? Collections.emptyList() : role.getGrantedAuthorities();
  }

  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonProperty("organization")
  public Organization getOrganization() {
    return organization;
  }
}
