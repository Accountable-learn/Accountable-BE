// package com.accountable.entity;
//
//
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import jakarta.persistence.*;
// import lombok.*;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;
//
// @NoArgsConstructor
// @AllArgsConstructor
// @Data
// @Entity
// @Table(name = Classroom.TABLE_NAME)
// public class Classroom extends AbstractUuidEntity {
//  public static final String TABLE_NAME = "classrooms";
//
//  @Column(name="org_id")
//  private UUID orgId;
//
//  @Column(name = "class_name")
//  private String className;
//
////  @Column(name = "teacher_ids")
////  private List<UUID> teacherId;
//
//  @Column(name = "question_pool_id")
//  private UUID questionPoolId;
//
////  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "classroom")
////  @ToString.Exclude
////  @JsonIgnore
////  private List<UserClassAssignment> userClassAssignments;
//
//  // first 7 digits of UUID is the class access code
//  public String getAccessCode() {
//   return this.getId().toString().substring(0, 7);
//  }
//
//  // get all the Users including teachers and students belongs to this class
////  public List<UUID> getUsers() {
////   if (null == userClassAssignments) {
////    return new ArrayList<>(1);
////   }
////   return userClassAssignments.stream().map(UserClassAssignment::getUserId).toList();
////  }
// }
