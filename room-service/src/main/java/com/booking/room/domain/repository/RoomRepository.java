package com.booking.room.domain.repository;

import com.booking.room.domain.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {
    void save(Room room);
    Optional<Room> findById(UUID id);
    List<Room> findAll();
}
