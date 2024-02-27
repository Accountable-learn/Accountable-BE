package com.accountable.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = UserClassAssignment.TABLE_NAME)
//public class UserClassAssignment extends AbstractUuidEntity {
//    public static final String TABLE_NAME = "user_class_assignment";
//
//    @NotNull
//    private UUID userId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "classroom_id", referencedColumnName = "id", nullable = false)
//    @ToString.Exclude
//    @JsonIgnore
//    private Classroom classroom;
//
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("classroomId")
//    public Classroom getClassroom() {
//        return classroom;
//    }
//}


