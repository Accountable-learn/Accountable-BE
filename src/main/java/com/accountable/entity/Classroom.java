package com.accountable.entity;

import com.accountable.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = Classroom.TABLE_NAME)
public class Classroom extends AbstractUuidEntity {
  public static final String TABLE_NAME = "classrooms";

  @Column(name = "org_id")
  @NotNull
  private UUID orgId;

  @Column(name = "class_name")
  private String className;

  // by default is the first 7 digits of UUID
  @Column(name = "code")
  private String code;

  // A Classroom can be mapped to multiple users
  // and multiple users can be mapped to one Classroom
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "classroom")
  @ToString.Exclude
  @JsonIgnore
  private List<UserClassMapping> userClassMappings;

  // A Classroom can be mapped to multiple questionBanks
  @OneToMany(
      mappedBy = "classroom",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  @ToString.Exclude
  @JsonIgnore
  private List<ClassQuestionBankMapping> questionBankMappings;

  // get all the Students belongs to this class (for jackson)
  // Doesn't make much sense to include other teachers...
  public List<User> getUsers() {
    if (null == userClassMappings) {
      return new ArrayList<>(1);
    }
    return userClassMappings.stream()
        .map(UserClassMapping::getUser)
        .filter(user -> user.getRole() == Role.STUDENT)
        .collect(Collectors.toList());
  }

  // get a list of question banks belonging to this classroom (for jackson)
  public List<UUID> getQuestionBanks() {
    if (questionBankMappings == null) {
      return new ArrayList<>(1);
    }
    return questionBankMappings.stream()
        .map(mapping -> mapping.getQuestionBank().getId())
        .collect(Collectors.toList());
  }
}
