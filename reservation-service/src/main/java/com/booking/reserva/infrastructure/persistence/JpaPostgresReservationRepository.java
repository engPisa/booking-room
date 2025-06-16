package com.booking.reserva.infrastructure.persistence;

import com.booking.reserva.domain.model.Reservation;
import com.booking.reserva.domain.model.TimeSlot;
import com.booking.reserva.domain.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaPostgresReservationRepository implements ReservationRepository {

    private final JpaReservationRepository jpaRepository;
    private final JpaReservationMapper jpaMapper;

    public JpaPostgresReservationRepository(JpaReservationRepository jpaRepository, JpaReservationMapper jpaMapper) {
        this.jpaRepository = jpaRepository;
        this.jpaMapper = jpaMapper;
    }

    @Override
    public void save(Reservation reservation){
        JpaReservationEntity entity = jpaMapper.toEntity(reservation);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        return jpaRepository.findById(id).map(jpaMapper::toDomain);
    }

    @Override
    public List<Reservation> findByRoomIdAndOverlappingTimeSlot(UUID roomId, TimeSlot timeSlot) {
        List<JpaReservationEntity> entities = jpaRepository.findOverlappingReservations(
                roomId, timeSlot.getStartTime(), timeSlot.getEndTime());
        return  jpaMapper.toDomainList(entities);
    }
}
