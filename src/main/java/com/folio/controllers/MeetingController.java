//package com.folio.controllers;
//
//import com.folio.dtos.MeetingFilterDTO;
//import com.folio.dtos.MeetingRequestDTO;
//import com.folio.models.Meeting;
//import com.folio.services.MeetingService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/meetings")
//public class MeetingController {
//
//    private final MeetingService meetingService;
//
//    public MeetingController(MeetingService meetingService) {
//        this.meetingService = meetingService;
//    }
//
//    // ------------------ GET by date ------------------
//    @GetMapping("/{id}/filter")
//    public Optional<Meeting> getMeetingsByDate(
//            @RequestParam("date") LocalDate date,
//            @PathVariable("id") Long id) {
//
//        return meetingService.getMeetingsByDate(date, id);
//    }
//
//    @GetMapping("/all")
//    public List<Meeting> getAllMeetings() {
//        return meetingService.getAllMeeting();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Meeting> getAllMeetingsById(@PathVariable Long id) {
//        return meetingService.getMeetingsByInitiatorId(id);
//    }
//    // ------------------ POST: Create a meeting ------------------
//    @PostMapping("/{id}")
//    public ResponseEntity<String> createMeeting(
//            @PathVariable("id") Long id,
//            @RequestBody MeetingRequestDTO dto) {
//
//        Meeting meeting = new Meeting();
//        meeting.setInitiatorId(id);
//        meeting.setCollaboratorsId(dto.getCollaboratorsId());
//        meeting.setStartTime(dto.getStartTime());
//        meeting.setEndTime(dto.getEndTime());
//        meeting.setAgenda(dto.getAgenda());
//
//        boolean success = meetingService.addMeeting(meeting);
//
//        if (!success) {
//            return ResponseEntity.badRequest().body("Meeting conflicts with an existing meeting!");
//        }
//
//        return ResponseEntity.ok("Meeting created successfully!");
//    }
//
//    // ------------------ POST: Filter by date + time ------------------
////    @PostMapping("/filter")
////    public Optional<Meeting> filterByDateAndTime(
////            @PathVariable("id") Long id,
////            @RequestBody MeetingFilterDTO dto) {
////
////        return meetingService.getMeetingsByDateAndTime(
////                dto.getDate(),
////                dto.getStartTime(),
////                dto.getEndTime(),
////                id
////        );
////    }
//}


package com.folio.controllers;

import com.folio.dtos.MeetingFilterDTO;
import com.folio.dtos.MeetingRequestDTO;
import com.folio.models.Meeting;
import com.folio.services.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    // ----------------------------------------------------
    // 1️⃣ CREATE MEETING  (POST /api/meetings/{id})
    // ----------------------------------------------------
    @PostMapping("/{id}")
    public ResponseEntity<String> createMeeting(
            @PathVariable Long id,
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

    // ----------------------------------------------------
    // 2️⃣ GET ALL MEETINGS (GET /api/meetings)
    // ----------------------------------------------------
    @GetMapping
    public List<Meeting> getAllMeetings() {
        return meetingService.getAllMeeting();
    }

    // ----------------------------------------------------
    // 3️⃣ GET MEETINGS BY ID  (GET /api/meetings/{id})
    // ----------------------------------------------------
    @GetMapping("/{id}")
    public Optional<Meeting> getMeetingsById(@PathVariable Long id) {
        return meetingService.getMeetingsByInitiatorId(id);
    }

    // ----------------------------------------------------
    // 4️⃣ FILTER MEETINGS BY ID + DATE
    //     GET /api/meetings/{id}/filter?date=2025-10-01
    // ----------------------------------------------------
    @GetMapping("/{id}/filter")
    public Optional<Meeting> filterMeetingsByIdAndDate(
            @PathVariable Long id,
            @RequestParam("date") LocalDate date) {

        return meetingService.getMeetingsByDate(date, id);
    }
}
