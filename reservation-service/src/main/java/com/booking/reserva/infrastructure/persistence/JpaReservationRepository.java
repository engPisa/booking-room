package com.booking.reserva.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JpaReservationRepository  extends JpaRepository<JpaReservationEntity, UUID> {

    @Query("SELECT r FROM ReservationEntity r WHERE r.roomId = :roomId AND r.startTime < :endTime AND r.endTime > :startTime")
    List<JpaReservationEntity> findOverlappingReservations(
            @Param("roomId") UUID roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

}
