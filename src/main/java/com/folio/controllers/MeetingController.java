package com.folio.controllers;

import com.folio.dtos.MeetingRequestDTO;
import com.folio.models.Meeting;
import com.folio.services.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    // ------------------ GET all meetings ------------------
    @GetMapping("/all")
    public List<Meeting> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    // ------------------ GET meetings by user ID ------------------
    @GetMapping("/{id}")
    public List<Meeting> getMeetingsByUser(@PathVariable Long id) {
        return meetingService.getMeetingsByUser(id);
    }

    // ------------------ GET meetings by user ID + date ------------------
    @GetMapping("/{id}/filter")
    public List<Meeting> getMeetingsByUserAndDate(
            @PathVariable Long id,
            @RequestParam("date") LocalDate date) {

        return meetingService.getMeetingsByUserAndDate(id, date);
    }

    // ------------------ POST: Create a meeting ------------------
    @PostMapping("/{id}")
    public ResponseEntity<String> createMeeting(@RequestBody MeetingRequestDTO dto, @PathVariable Long id) {

        boolean success = meetingService.addMeeting(dto, id);

        if (!success) {
            return ResponseEntity.badRequest().body("Meeting conflict detected!");
        }

        return ResponseEntity.ok("Meeting created successfully!");
    }
}
