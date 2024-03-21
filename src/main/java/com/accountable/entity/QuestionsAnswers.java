package com.accountable.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Each object can identify all the questions a user answers (fks) and their corresponding answers (json).
 * A teacher can also attached their feedback to the entity for feedback
 * **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = QuestionsAnswers.TABLE_NAME)
public class QuestionsAnswers extends AbstractUuidEntity{
    public static final String TABLE_NAME = "questions_answers";

    @Column(name = "user_id")
    private UUID userId;

    // Probably go with json
    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionsAnswers")
    //    @ToString.Exclude
    //    private List<Question> questions;

    @Column(name = "answers", columnDefinition = "jsonb")
    private String answers;

    @Column(name = "feedback", columnDefinition = "jsonb")
    private String feedback;

    @Column(name = "is_approved")
    private Boolean isApproved;
}