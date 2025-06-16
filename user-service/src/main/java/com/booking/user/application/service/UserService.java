package com.booking.user.application.service;

import com.booking.user.application.dto.DTORequest;
import com.booking.user.domain.model.Email;
import com.booking.user.domain.model.Endereco;
import com.booking.user.domain.model.Password;
import com.booking.user.domain.model.User;
import com.booking.user.domain.repository.UserRepository;
import com.booking.user.infrastructure.messaging.UserEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEventPublisher userEventPublisher;

    @Transactional
    public User registerUser(DTORequest request){
        Email emailVO = new Email(request.email());

        Endereco enderecoVO = new Endereco(
                request.rua(),
                request.cidade(),
                request.estado(),
                request.cep()
        );

        if (userRepository.existsByEmail(emailVO)){
            throw new IllegalStateException("E-mail já cadastrado.");
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        Password passwordVO = new Password(hashedPassword);

        User newUser = User.create(
                request.nome(),
                emailVO,
                passwordVO,
                enderecoVO,
                request.telefone());

        userRepository.save(newUser);
        userEventPublisher.publishReservationCreated(newUser);

        return newUser;
    }
}
