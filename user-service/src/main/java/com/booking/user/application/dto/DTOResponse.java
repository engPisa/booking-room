package com.booking.user.application.dto;

import com.booking.user.domain.model.User;

import java.util.UUID;

public record DTOResponse(
        UUID id,
        String nome,
        String email,
        String telefone,
        DTOAdress address
) { public static DTOResponse fromDomain(User user) {
        if (user == null) {
            return null;
        }
        return new DTOResponse(
                user.getId(),
                user.getNome(),
                user.getEmail().email(),
                user.getTelefone(),
                DTOAdress.fromDomain(user.getEndereco())
        );
    }
}
