package com.booking.reserva.domain.repository;

import com.booking.reserva.domain.model.Reservation;
import com.booking.reserva.domain.model.TimeSlot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {

    void save(Reservation reservation);

    Optional<Reservation> findById(UUID id);

    List<Reservation> findByRoomIdAndOverlappingTimeSlot(UUID roomId, TimeSlot timeSlot);
}
