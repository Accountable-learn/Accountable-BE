package com.accountable.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = ClassQuestionBankMapping.TABLE_NAME)
public class ClassQuestionBankMapping extends AbstractUuidEntity{
    public static final String TABLE_NAME = "class_bank_mapping";

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "question_bank_id", referencedColumnName = "id")
    private QuestionBank questionBank;
}
