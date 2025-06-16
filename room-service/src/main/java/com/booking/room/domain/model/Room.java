package com.booking.room.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Room {
    private UUID id;
    private String name;
    private String location;
    private Capacity capacity;

    private Room(){
    }

    public static Room create(String name,
                              String location,
                              Capacity capacity){
        if (name == null || location == null || capacity == null || location.isBlank()){
            throw new IllegalArgumentException("Nome e localização são obrigatórios.");
        }
        Room room = new Room();
        room.id = UUID.randomUUID();
        room.name = name;
        room.location = location;
        room.capacity = capacity;
        return room;
    }

    private void updateDetails(String newName,
                               String newLocation,
                               Capacity newCapacity){
        if (newName == null || newLocation == null || newCapacity == null || newLocation.isBlank()){
            throw new IllegalArgumentException("Nome e localização são obrigatórios.");
        }
        this.name = newName;
        this.location = newLocation;
        this.capacity = newCapacity;
    }

    public static Room reconstitute(UUID id,
                                    String name,
                                    String location,
                                    Capacity capacity){
        Room room = new Room();
        room.id = id;
        room.name = name;
        room.location = location;
        room.capacity = capacity;
        return room;
    }
}
