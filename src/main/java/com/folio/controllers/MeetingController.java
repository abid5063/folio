package com.folio.controllers;

import com.folio.dtos.MeetingFilterDTO;
import com.folio.dtos.MeetingRequestDTO;
import com.folio.models.Meeting;
import com.folio.services.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/{id}/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    // ------------------ GET by date ------------------
    @GetMapping("/filter")
    public Optional<Meeting> getMeetingsByDate(
            @RequestParam("date") LocalDate date,
            @PathVariable("id") Long id) {

        return meetingService.getMeetingsByDate(date, id);
    }

    @GetMapping
    public Optional<Meeting> getAllMeetings(@PathVariable Long id) {
        return meetingService.getMeetingsByInitiatorId(id);
    }
    // ------------------ POST: Create a meeting ------------------
    @PostMapping
    public ResponseEntity<String> createMeeting(
            @PathVariable("id") Long id,
            @RequestBody MeetingRequestDTO dto) {

        Meeting meeting = new Meeting();
        meeting.setInitiatorId(id);
        meeting.setCollaboratorsId(dto.getCollaboratorsId());
        meeting.setStartTime(dto.getStartTime());
        meeting.setEndTime(dto.getEndTime());
        meeting.setAgenda(dto.getAgenda());

        boolean success = meetingService.addMeeting(meeting);

        if (!success) {
            return ResponseEntity.badRequest().body("Meeting conflicts with an existing meeting!");
        }

        return ResponseEntity.ok("Meeting created successfully!");
    }

    // ------------------ POST: Filter by date + time ------------------
    @PostMapping("/filter")
    public Optional<Meeting> filterByDateAndTime(
            @PathVariable("id") Long id,
            @RequestBody MeetingFilterDTO dto) {

        return meetingService.getMeetingsByDateAndTime(
                dto.getDate(),
                dto.getStartTime(),
                dto.getEndTime(),
                id
        );
    }
}
