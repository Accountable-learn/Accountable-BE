package com.accountable.dto;


import jakarta.persistence.Column;

import java.util.UUID;

public class UserAnswerDto {
    UUID questionUuid;
    UUID userId;
    int wordCount;
}
