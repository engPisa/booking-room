package com.booking.user.infrastructure.persistence;

import com.booking.user.domain.model.Email;
import com.booking.user.domain.model.Endereco;
import com.booking.user.domain.model.Password;
import com.booking.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class JpaUserMapper {

    public JpaUserEntity toEntity(User user){
        if (user == null)
            return null;

        return new JpaUserEntity(
                user.getId(),
                user.getNome(),
                user.getEmail().email(),
                user.getPassword().hashedPassword(),
                user.getTelefone(),
                user.getEndereco().rua(),
                user.getEndereco().cidade(),
                user.getEndereco().estado(),
                user.getEndereco().cep()
        );
    }

    public User toDomain(JpaUserEntity entity){
        if (entity == null)
            return null;

        return User.reconstitute(
                entity.getId(),
                entity.getNome(),
                new Email(entity.getEmail()),
                new Password(entity.getPasswordHash()),
                new Endereco(
                        entity.getRua(),
                        entity.getCidade(),
                        entity.getEstado(),
                        entity.getCep()
                ),
                entity.getTelefone()
        );
    }
}
