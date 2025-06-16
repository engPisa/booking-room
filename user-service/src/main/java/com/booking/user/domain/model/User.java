package com.booking.user.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String nome;
    private Email email;
    private Password password;
    private Endereco endereco;
    private String telefone;

    private User(){}

    public static User create(String nome,
                              Email email,
                              Password password,
                              Endereco endereco,
                              String telefone){
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");

        User user = new User();
        user.id = UUID.randomUUID();
        user.nome = nome;
        user.email = email;
        user.password = password;
        user.endereco = endereco;
        user.telefone = telefone;

        return user;
    }

    public static User reconstitute(UUID id,
                                    String nome,
                                    Email email,
                                    Password password,
                                    Endereco endereco,
                                    String telefone){
        User user = new User();
        user.id = id;
        user.nome = nome;
        user.email = email;
        user.endereco = endereco;
        user.telefone = telefone;
        user.password = password;

        return user;
    }
}
