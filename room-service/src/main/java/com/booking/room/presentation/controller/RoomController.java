package com.booking.room.presentation.controller;

import com.booking.room.application.dto.DTORequest;
import com.booking.room.application.dto.DTOResponse;
import com.booking.room.application.service.RoomService;
import com.booking.room.domain.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<DTOResponse> getRoomById(@PathVariable UUID id) {
        return roomService.FindRoomById(id)
                .map(room -> ResponseEntity.ok(DTOResponse.fromDomain(room)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DTOResponse> create(@RequestBody DTORequest request) {
        Room newRoom = roomService.createRoom(
                request.name(),
                request.location(),
                request.capacity());
        return ResponseEntity.status(HttpStatus.CREATED).body(DTOResponse.fromDomain(newRoom));
    }
}
