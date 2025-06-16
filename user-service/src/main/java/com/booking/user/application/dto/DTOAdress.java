package com.booking.user.application.dto;

import com.booking.user.domain.model.Endereco;

public record DTOAdress(
        String rua,
        String cidade,
        String estado,
        String cep
) {
    public static DTOAdress fromDomain(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new DTOAdress(
                endereco.rua(),
                endereco.cidade(),
                endereco.estado(),
                endereco.cep()
        );
    }
}