//package com.accountable.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.vladmihalcea.hibernate.type.array.StringArrayType;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.JdbcTypeCode;
//import org.hibernate.annotations.Type;
//import org.hibernate.type.SqlTypes;
//
//import java.util.List;
//import java.util.UUID;
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = UserAnswersQuestionsAssignment.TABLE_NAME)
//public class UserAnswersQuestionsAssignment extends AbstractUuidEntity{
//    public static final String TABLE_NAME = "user_questions_answers_assignment";
//
//    @Column(name = "user_id")
//    private UUID userId;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questions")
//    @ToString.Exclude
//    @JsonIgnore
//    private List<Question> questions;
//
//    private List<String> answers;
//}