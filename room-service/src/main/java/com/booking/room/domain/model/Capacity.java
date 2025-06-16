package com.booking.room.domain.model;

public record Capacity(int value) {
    public Capacity{
        if (value<=0){
            throw new IllegalArgumentException("A capacidade deve ser um número positivo.");
        }
    }
}
