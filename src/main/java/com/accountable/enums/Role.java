package com.accountable.enums;

import static com.accountable.enums.Permission.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/***
 * Design: Higher level roles will contains lower level role's permissions
 * **/

@Getter
@RequiredArgsConstructor
public enum Role {
  // admin role as all the permissions
  ADMIN(Stream.of(Permission.values()).collect(Collectors.toSet())),
  TEACHER(Set.of(TEACHER_READ, TEACHER_UPDATE, TEACHER_DELETE, TEACHER_CREATE)),
  STUDENT(Set.of(STUDENT_READ, STUDENT_UPDATE, STUDENT_DELETE, STUDENT_CREATE));

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getGrantedAuthorities() {
    var authorities =
        getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }

  @JsonCreator
  public static Role of(String name) {
    return Stream.of(Role.values())
        .filter(role -> role.name().equalsIgnoreCase(name))
        .findFirst()
        .orElse(Role.STUDENT); // student as a default role
  }
}
