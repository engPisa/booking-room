package com.booking.room.infrastructure.persistence;

import com.booking.room.domain.model.Capacity;
import com.booking.room.domain.model.Room;
import org.springframework.stereotype.Component;

@Component
public class JpaRoomMapper {
    public JpaRoomEntity toEntity(Room room) {
        JpaRoomEntity entity = new JpaRoomEntity();
        entity.setId(room.getId());
        entity.setName(room.getName());
        entity.setLocation(room.getLocation());
        entity.setCapacity(room.getCapacity().value());
        return entity;
    }

    public Room toDomain(JpaRoomEntity entity) {
        return Room.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getLocation(),
                new Capacity(entity.getCapacity())
        );
    }
}
