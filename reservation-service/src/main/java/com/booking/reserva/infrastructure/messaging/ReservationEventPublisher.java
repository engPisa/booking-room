package com.booking.reserva.infrastructure.messaging;

import com.booking.reserva.application.dto.DTOResponse;
import com.booking.reserva.domain.model.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishReservationCreated(Reservation reservation){
        DTOResponse event = DTOResponse.fromDomain(reservation);

        String routingKey = "reservation.reservation.created";

        log.info("Publicando evento de reserva criada para a exchange '{}' com a chave '{}'. ID da Reserva: {}",
                RabbitMQConfig.BOOKING_SYSTEM, routingKey, reservation.getId());

        rabbitTemplate.convertAndSend(RabbitMQConfig.BOOKING_SYSTEM,routingKey, event);
    }
}
