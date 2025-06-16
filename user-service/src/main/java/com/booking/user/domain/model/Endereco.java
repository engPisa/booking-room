package com.booking.user.domain.model;

public record Endereco(
        String rua,
        String cidade,
        String estado,
        String cep
) {}
