package com.booking.room.infrastructure.messaging;

import com.booking.room.application.dto.DTOResponse;
import com.booking.room.domain.model.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishRoomCreated(Room room){
        DTOResponse event = DTOResponse.fromDomain(room);

        String routingKey = "room.room.created";

        log.info("Publicando evento de sala criada para a exchange '{}' com a chave '{}'. ID da Reserva: {}",
                RabbitMQConfig.BOOKING_SYSTEM, routingKey, room.getId());

        rabbitTemplate.convertAndSend(RabbitMQConfig.BOOKING_SYSTEM,routingKey, event);
    }
}