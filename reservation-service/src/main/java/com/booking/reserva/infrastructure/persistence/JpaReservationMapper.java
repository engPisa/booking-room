package com.booking.reserva.infrastructure.persistence;

import com.booking.reserva.domain.model.Reservation;
import com.booking.reserva.domain.model.TimeSlot;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaReservationMapper {
    public JpaReservationEntity toEntity(Reservation reservation) {
        if (reservation == null){
            return null;
        }

        JpaReservationEntity entity = new JpaReservationEntity();
        entity.setId(reservation.getId());
        entity.setUserId(reservation.getUserId());
        entity.setRoomId(reservation.getRoomId());
        entity.setStatus(reservation.getStatus());
        entity.setStartTime(reservation.getTimeSlot().getStartTime());
        entity.setEndTime(reservation.getTimeSlot().getEndTime());
        return entity;
    }

    public Reservation toDomain(JpaReservationEntity entity){
        if (entity == null){
            return null;
        }

        TimeSlot timeSlot = new TimeSlot(entity.getStartTime(), entity.getEndTime());

        return Reservation.reconstitute(
                entity.getId(),
                entity.getUserId(),
                entity.getRoomId(),
                timeSlot,
                entity.getStatus()
        );
    }

    public List<Reservation> toDomainList(List<JpaReservationEntity> entities){
        return entities.stream().map(
                this::toDomain
        ).collect(Collectors.toList());
    }
}

