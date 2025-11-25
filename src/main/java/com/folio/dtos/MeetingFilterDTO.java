package com.folio.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MeetingFilterDTO {
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
