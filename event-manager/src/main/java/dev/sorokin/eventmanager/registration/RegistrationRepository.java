package dev.sorokin.eventmanager.registration;

import dev.sorokin.eventmanager.event.EventEntity;
import dev.sorokin.eventmanager.user.UserEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {


    @Query("SELECT r FROM RegistrationEntity r " +
            "JOIN FETCH r.user " +
            "JOIN FETCH r.event " +
            "WHERE r.user.id = :currentUserId AND r.event.id = :eventId")
    Optional<RegistrationEntity> findCurrentUserRegistrationOnEvent(
            @Param("currentUserId") Long currentUserId,
            @Param("eventId") Long eventId
    );

    @Query("SELECT r.user.id FROM RegistrationEntity r WHERE r.event.id = :eventId")
    List<Long> findUserIdsByEventId(@Param("eventId") Long eventId);

    @Query(value = """
        SELECT r.* FROM registration r
        JOIN event e ON e.id = r.event_id
        WHERE r.user_id = :currentUserId
          AND e.start_at < :eventEndAt
          AND e.start_at + e.duration_minutes * INTERVAL '1 minute' > :eventStartAt
        LIMIT 3
    """, nativeQuery = true)
    List<RegistrationEntity> findAllCurrentUserRegistrationsOnThisTime(
            @Param("currentUserId") Long currentUserId,
            @Param("eventStartAt") LocalDateTime eventStartAt,
            @Param("eventEndAt") LocalDateTime eventEndAt
    );
}
