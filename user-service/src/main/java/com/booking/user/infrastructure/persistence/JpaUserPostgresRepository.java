package com.booking.user.infrastructure.persistence;

import com.booking.user.domain.model.Email;
import com.booking.user.domain.model.User;
import com.booking.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaUserPostgresRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserMapper jpaUserMapper;

    @Override
    public void save(User user){
        JpaUserEntity entity = jpaUserMapper.toEntity(user);
        jpaUserRepository.save(entity);
    }

    @Override
    public Optional<User>findById(UUID id){
        return jpaUserRepository.findById(id)
                .map(jpaUserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email){
        return jpaUserRepository.findByEmail(email.email())
                .map(jpaUserMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email){
        return jpaUserRepository.existsByEmail(email.email());
    }
}
