package com.booking.user.application.service;

import com.booking.user.application.dto.DTOLogin;
import com.booking.user.domain.model.Email;
import com.booking.user.domain.model.User;
import com.booking.user.domain.repository.UserRepository;
import com.booking.user.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(DTOLogin request){
        Email emailVO = new Email(request.email());

        User user = userRepository.findByEmail(emailVO)
                .orElseThrow(() -> new BadCredentialsException("Usuário ou senha invalidos."));

        if (!passwordEncoder.matches(request.password(), user.getPassword().hashedPassword())){
            throw new BadCredentialsException("Usuário ou senha inválidos.");
        }
        return jwtService.generateToken(user.getEmail().email());
    }
}
