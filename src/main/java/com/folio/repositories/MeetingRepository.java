package com.folio.repositories;

import com.folio.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository <Meeting, Long> {
    Optional<Meeting> findByInitiatorId(Long id);

    @Query("""
        SELECT m FROM Meeting m
        WHERE DATE(m.startTime) = :date
        AND m.initiatorId = :initiatorId
        """)
    Optional<Meeting> findByDate(@Param("date") LocalDate date,
                                 @Param("initiatorId") Long initiatorId);

    @Query("""
        SELECT m FROM Meeting m
        WHERE DATE(m.startTime) = :date
          AND m.startTime >= :startTime
          AND m.endTime <= :endTime
          AND m.initiatorId = :initiatorId
        """)
    Optional<Meeting> findByDateAndTime(
            @Param("date") LocalDate date,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("initiatorId") Long initiatorId
    );

    @Query("""
        SELECT m FROM Meeting m
        WHERE m.initiatorId = :initiatorId
          AND m.startTime < :endTime
          OR m.endTime > :startTime
        """)
    Optional<Meeting> findConflictingMeeting(
            @Param("initiatorId") Long initiatorId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
