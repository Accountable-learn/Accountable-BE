 package com.accountable.entity;


 import com.fasterxml.jackson.annotation.JsonIgnore;
 import jakarta.persistence.*;
 import lombok.*;

 import java.util.*;
 import java.util.stream.Collectors;

 @NoArgsConstructor
 @AllArgsConstructor
 @Data
 @Entity
 @Table(name = Classroom.TABLE_NAME)
 public class Classroom extends AbstractUuidEntity {
  public static final String TABLE_NAME = "classrooms";

  @Column(name="org_id")
  private UUID orgId;

  @Column(name = "class_name")
  private String className;

  // A Classroom can be mapped to multiple users
  // and multiple users can be mapped to one Classroom
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "classroom")
  @ToString.Exclude
  @JsonIgnore
  private List<UserClassMapping> userClassMappings;

  // A Classroom can be mapped to multiple questionBanks
  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @ToString.Exclude
  @JsonIgnore
  private Set<ClassQuestionBankMapping> questionBankMappings = new HashSet<>();

  // first 7 digits of UUID is the class access code
  public String getAccessCode() {
   return this.getId().toString().substring(0, 7);
  }

  // get all the Users belongs to this class (including teachers and students)
  public List<UUID> getUsers() {
   if (null == userClassMappings) {
    return new ArrayList<>(1);
   }
   return userClassMappings.stream().map(UserClassMapping::getUserId).toList();
  }

  // get a list of question banks belonging to this classroom
  public List<UUID> getQuestionBankIds() {
   if (questionBankMappings == null) {
    return new ArrayList<>(1);
   }
   return questionBankMappings.stream()
           .map(mapping -> mapping.getQuestionBank().getId())
           .collect(Collectors.toList());
  }
 }
