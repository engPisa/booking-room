package com.booking.reserva.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DTORequest(
    UUID userId,
    UUID roomId,
    LocalDateTime startTime,
    LocalDateTime endTime
){}