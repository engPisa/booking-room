package com.booking.room.application.dto;

import com.booking.room.domain.model.Room;

import java.util.UUID;

public record DTOResponse(
        UUID id,
        String name,
        String location,
        int capacity
) { public static DTOResponse fromDomain(Room room) {
        return new DTOResponse(
                room.getId(),
                room.getName(),
                room.getLocation(),
                room.getCapacity().value()
        );
    }
}
