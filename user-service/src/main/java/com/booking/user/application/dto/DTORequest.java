package com.booking.user.application.dto;

public record DTORequest(
        String nome,
        String email,
        String password,
        String telefone,
        String rua,
        String cidade,
        String estado,
        String cep
) {}
