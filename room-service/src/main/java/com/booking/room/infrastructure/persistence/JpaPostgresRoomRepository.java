package com.booking.room.infrastructure.persistence;

import com.booking.room.domain.model.Room;
import com.booking.room.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaPostgresRoomRepository implements RoomRepository {

    private  final JpaRoomRepository jpaRepository;
    private final JpaRoomMapper jpaRoomMapper;

    @Override
    public void save(Room room) {
        JpaRoomEntity entity = jpaRoomMapper.toEntity(room);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Room> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(jpaRoomMapper::toDomain);
    }

    @Override
    public List<Room> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(jpaRoomMapper::toDomain)
                .collect(Collectors.toList());
    }
}
