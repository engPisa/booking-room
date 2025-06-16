package com.booking.reserva.application.dto;

import com.booking.reserva.domain.model.Reservation;
import com.booking.reserva.domain.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record DTOResponse(
        UUID id,
        UUID userId,
        UUID roomId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ReservationStatus status
) {
    public static DTOResponse fromDomain(Reservation reservation) {
        return new DTOResponse(
                reservation.getId(),
                reservation.getUserId(),
                reservation.getRoomId(),
                reservation.getTimeSlot().getStartTime(),
                reservation.getTimeSlot().getEndTime(),
                reservation.getStatus()
        );
    }
}
