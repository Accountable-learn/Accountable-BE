package com.accountable.response;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse {
    private OffsetDateTime timestamp;
    private int status;
    private String message;
}
