package com.booking.reserva.domain.model;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Reservation {
    private UUID id;
    private UUID userId;
    private UUID roomId;
    private TimeSlot timeSlot;
    private ReservationStatus status;

    private Reservation(){
    }

    public static Reservation create(UUID userId,
                                     UUID roomId,
                                     TimeSlot timeSlot){
        if(userId == null || roomId == null || timeSlot == null){
            throw new IllegalArgumentException(
                    "Usuário, sala e timeSlot não pode ser null");
        }

        Reservation reservation = new Reservation();
        reservation.id = UUID.randomUUID();
        reservation.userId = userId;
        reservation.roomId = roomId;
        reservation.timeSlot = timeSlot;
        reservation.status = ReservationStatus.PENDING;
        return reservation;
    }

    public void confirm(){
        if (this.status != ReservationStatus.PENDING){
            throw new IllegalStateException(
                    "Somente reservas pendentes podem ser confirmadas.");
        }
        this.status = ReservationStatus.CONFIRMED;
    }

    public static Reservation reconstitute(UUID id, UUID userId,
                                           UUID roomId, TimeSlot timeSlot,
                                           ReservationStatus status){
        Reservation reservation = new Reservation();
        reservation.id = id;
        reservation.userId = userId;
        reservation.roomId = roomId;
        reservation.timeSlot = timeSlot;
        reservation.status = status;
        return reservation;
    }

    public void cancel(){
        if (this.status == ReservationStatus.CANCELLED){
            throw new IllegalStateException(
                    "A reserva já foi cancelada.");
        }
        this.status = ReservationStatus.CANCELLED;
    }
}
