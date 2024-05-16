package com.accountable.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = UserClassMapping.TABLE_NAME)
public class UserClassMapping extends AbstractUuidEntity {
  public static final String TABLE_NAME = "user_class_mapping";

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
  @ToString.Exclude
  @JsonIgnore
  @NotNull
  private User user;

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

  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  @JsonProperty("userId")
  public User getUser() {
    return user;
  }

  @Column(name = "is_approved")
  private Boolean isApproved = false;
}
