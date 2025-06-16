package com.booking.user.infrastructure.messaging;

import com.booking.user.application.dto.DTOResponse;
import com.booking.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishReservationCreated(User user){
        DTOResponse event = DTOResponse.fromDomain(user);

        String routingKey = "user.user.created";

        log.info("Publicando evento de usuário criado para a exchange '{}' com a chave '{}'. ID da Reserva: {}",
                RabbitMQConfig.BOOKING_SYSTEM, routingKey, user.getId());

        rabbitTemplate.convertAndSend(RabbitMQConfig.BOOKING_SYSTEM,routingKey, event);
    }
}
