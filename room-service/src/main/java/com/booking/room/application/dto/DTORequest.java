package com.booking.room.application.dto;

public record DTORequest(
        String name,
        String location,
        int capacity
) {}

