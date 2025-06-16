package com.booking.room.infrastructure.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String BOOKING_SYSTEM = "booking_events_exchange";

    @Bean
    public TopicExchange reservationExchange() {
        return new TopicExchange(BOOKING_SYSTEM);
    }
}
