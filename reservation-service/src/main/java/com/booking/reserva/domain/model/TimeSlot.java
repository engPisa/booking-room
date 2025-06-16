package com.booking.reserva.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class TimeSlot {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public TimeSlot(final LocalDateTime startTime, final LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("O horário de início deve ser anterior ao horário de término.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public boolean overlapsWith(TimeSlot other) {
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }
}
