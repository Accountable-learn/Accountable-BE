package com.accountable.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
  ADMIN_READ("admin:read"),
  ADMIN_UPDATE("admin:update"),
  ADMIN_CREATE("admin:create"),
  ADMIN_DELETE("admin:delete"),

  TEACHER_READ("teacher:read"),
  TEACHER_UPDATE("teacher:update"),
  TEACHER_CREATE("teacher:create"),
  TEACHER_DELETE("teacher:delete"),

  STUDENT_READ("student:read"),
  STUDENT_UPDATE("student:update"),
  STUDENT_CREATE("student:create"),
  STUDENT_DELETE("student:delete");

  private final String permission;

  /*Json to Enum object */
  @JsonCreator
  public static Permission of(String name) {
    return Stream.of(Permission.values())
        .filter(permission -> permission.name().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }
}
