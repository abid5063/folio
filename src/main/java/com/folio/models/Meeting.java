package com.folio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "meetings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    private Long initiatorId;

    // Stored as a comma-separated list (as in the DB)
    private String collaboratorsId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String agenda;
}