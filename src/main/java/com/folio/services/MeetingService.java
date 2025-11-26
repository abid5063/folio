package com.folio.services;

import com.folio.models.Meeting;
import com.folio.repositories.MeetingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<Meeting> getAllMeeting() {
        return meetingRepository.findAll();
    }

    public Optional<Meeting> getMeetingsByInitiatorId(Long id) {
        return meetingRepository.findByInitiatorId(id);
    }

    public Optional<Meeting> getConflictingMeeting(Meeting meeting) {
        return meetingRepository.findConflictingMeeting(
                meeting.getInitiatorId(),
                meeting.getStartTime(),
                meeting.getEndTime()
        );
    }

    public boolean addMeeting(Meeting meeting) {
        // If a conflict exists → do NOT save
        if (getConflictingMeeting(meeting).isPresent()) {
            return false;
        }
        meetingRepository.save(meeting);
        return true;
    }

    public Optional<Meeting> getMeetingsByDate(LocalDate date, Long initiatorId) {
        return meetingRepository.findByDate(date, initiatorId);
    }

    public Optional<Meeting> getMeetingsByDateAndTime(LocalDate date,
                                                      LocalDateTime start,
                                                      LocalDateTime end,
                                                      Long initiatorId) {
        return meetingRepository.findByDateAndTime(date, start, end, initiatorId);
    }
}
