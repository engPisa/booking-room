package com.booking.user.presentation.controller;

import com.booking.user.application.dto.DTORequest;
import com.booking.user.application.dto.DTOResponse;
import com.booking.user.application.service.UserService;
import com.booking.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<DTOResponse> registerUser(@RequestBody DTORequest request) {
        try {

            User newUser = userService.registerUser(request);

            DTOResponse response = DTOResponse.fromDomain(newUser);

            return ResponseEntity
                    .created(URI.create("/api/users/" + response.id()))
                    .body(response);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}