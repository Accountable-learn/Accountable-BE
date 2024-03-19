package com.accountable.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Locale;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = UserClassMapping.TABLE_NAME)
public class UserClassMapping extends AbstractUuidEntity {
    public static final String TABLE_NAME = "user_class_mapping";

    @NotNull
    @Column(name="user_id")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Classroom classroom;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("classroomId")
    public Classroom getClassroom() {
        return classroom;
    }

    @Column(name="is_approved")
    private Boolean isApproved = false;
}


