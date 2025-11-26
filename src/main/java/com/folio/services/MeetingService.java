package com.folio.services;

import com.folio.dtos.MeetingRequestDTO;
import com.folio.models.Meeting;
import com.folio.repositories.MeetingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // ------------------------- CREATE MEETING -------------------------
    public boolean addMeeting(MeetingRequestDTO dto, Long id) {

        // Parse collaborator IDs into List<Long>
        List<Long> collaborators = dto.getCollaboratorsId() == null || dto.getCollaboratorsId().isBlank()
                ? new ArrayList<>()
                : Arrays.stream(dto.getCollaboratorsId().split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .toList();


        // 1) Check conflict for every collaborator + initiator
        List<Long> everyone = new ArrayList<>(collaborators);
        everyone.add(id);

        for (Long i : everyone) {
            List<Meeting> conflicts = meetingRepository.findConflicts(
                    i,
                    dto.getStartTime(),
                    dto.getEndTime()
            );

            if (!conflicts.isEmpty()) {
                return false;  // conflict found
            }
        }

        // 2) Save new meeting
        Meeting meeting = new Meeting();
        meeting.setInitiatorId(id);
        meeting.setCollaboratorsId(dto.getCollaboratorsId());
        meeting.setStartTime(dto.getStartTime());
        meeting.setEndTime(dto.getEndTime());
        meeting.setAgenda(dto.getAgenda());

        meetingRepository.save(meeting);
        return true;
    }

    // ------------------------- GET ALL MEETINGS -------------------------
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    // ------------------------- GET USER MEETINGS -------------------------
    public List<Meeting> getMeetingsByUser(Long id) {
        return meetingRepository.findAllByUser(id);
    }

    // ------------------------- GET USER MEETINGS BY DATE -------------------------
    public List<Meeting> getMeetingsByUserAndDate(Long id, LocalDate date) {
        LocalDateTime dateStart = date.atStartOfDay();
        return meetingRepository.findByUserAndDate(id, dateStart);
    }
}
