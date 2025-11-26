package com.folio.repositories;

import com.folio.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    // Find meetings where user is initiator OR in collaborators list
    @Query("SELECT m FROM Meeting m WHERE m.initiatorId = :userId OR m.collaboratorsId LIKE %:userId%")
    List<Meeting> findAllByUser(Long userId);

    // Find meetings for a user on a specific date
    @Query("""
        SELECT m FROM Meeting m 
        WHERE (m.initiatorId = :userId OR m.collaboratorsId LIKE %:userId%)
        AND DATE(m.startTime) = DATE(:date)
    """)
    List<Meeting> findByUserAndDate(Long userId, LocalDateTime date);

    // Check time conflict for a collaborator
    @Query("""
        SELECT m FROM Meeting m 
        WHERE (m.initiatorId = :collabId OR m.collaboratorsId LIKE %:collabId%)
        AND m.startTime < :endTime
        AND m.endTime > :startTime
    """)
    List<Meeting> findConflicts(Long collabId, LocalDateTime startTime, LocalDateTime endTime);
}
