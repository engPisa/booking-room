package com.booking.reserva.presentation.controller;

import com.booking.reserva.application.dto.DTORequest;
import com.booking.reserva.application.dto.DTOResponse;
import com.booking.reserva.application.service.ReservationService;
import com.booking.reserva.domain.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<DTOResponse>createReservation(@RequestBody DTORequest request) {
        try{
            Reservation newReservation =  reservationService.create(
                    request.userId(),
                    request.roomId(),
                    request.startTime(),
                    request.endTime()
            );
            DTOResponse response = DTOResponse.fromDomain(newReservation);

            return ResponseEntity.created(URI.create("/api/reservation/" + response.id()))
                    .body(response);
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
