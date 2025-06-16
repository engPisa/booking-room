package com.booking.user.domain.model;

public record Password(String hashedPassword) {
    public Password{
        if (hashedPassword == null || hashedPassword.isBlank()){
            throw new IllegalArgumentException("Hash da senha não pode ser nula ou vazia.");
        }
    }
}
