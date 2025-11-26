package com.folio.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MeetingRequestDTO {
    private String collaboratorsId;   // comma-separated string
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String agenda;
}
