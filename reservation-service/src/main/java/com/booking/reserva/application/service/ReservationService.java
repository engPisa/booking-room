package com.booking.reserva.application.service;

import com.booking.reserva.domain.model.Reservation;
import com.booking.reserva.domain.model.TimeSlot;
import com.booking.reserva.domain.repository.ReservationRepository;
import com.booking.reserva.infrastructure.messaging.ReservationEventPublisher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationEventPublisher eventPublisher;

    public ReservationService(ReservationRepository reservationRepository,
                              ReservationEventPublisher eventPublisher) {
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Reservation create(UUID userId, UUID roomId,
                              LocalDateTime startDate,
                              LocalDateTime endDate) {

        TimeSlot newTimeSlot = new TimeSlot(startDate, endDate);

        List<Reservation> conflictingReservations = reservationRepository
                .findByRoomIdAndOverlappingTimeSlot(roomId, newTimeSlot);

        if (conflictingReservations.isEmpty()){
            throw new IllegalStateException("Conflito de agendamento: O horário para esta sala já está reservado.");
        }

        Reservation newReservation = Reservation.create(userId, roomId, newTimeSlot);
        reservationRepository.save(newReservation);

        eventPublisher.publishReservationCreated(newReservation);

        return newReservation;
    }
}
