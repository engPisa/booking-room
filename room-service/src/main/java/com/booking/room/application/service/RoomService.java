package com.booking.room.application.service;

import com.booking.room.domain.model.Capacity;
import com.booking.room.domain.model.Room;
import com.booking.room.domain.repository.RoomRepository;
import com.booking.room.infrastructure.messaging.RoomEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomEventPublisher eventPublisher;

    @Transactional
    public Room createRoom(String name, String location, int capacity) {
        Capacity capacityVO = new Capacity(capacity);

        Room newRoom = Room.create(name, location, capacityVO);

        roomRepository.save(newRoom);

        eventPublisher.publishRoomCreated(newRoom);

        return newRoom;
    }

    public Optional<Room> FindRoomById(UUID id) {
        return roomRepository.findById(id);
    }

    public List<Room> findAllRooms(){
        return roomRepository.findAll();
    }
}
