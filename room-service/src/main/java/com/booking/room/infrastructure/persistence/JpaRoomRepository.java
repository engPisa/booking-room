package com.booking.room.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaRoomRepository extends JpaRepository<JpaRoomEntity, UUID> {
}
