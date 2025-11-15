package com.goutamthakur.flight.auth.infrastructure.rabbitmq;

import com.goutamthakur.flight.auth.domain.event.UserRegisteredEvent;
import com.goutamthakur.flight.auth.domain.port.UserEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisteredPublisher implements UserEventPublisherPort {
    private final AmqpTemplate amqpTemplate;

    public void publishUserRegisteredEvent(UserRegisteredEvent event){
        amqpTemplate.convertAndSend(
                "",
                RabbitMQConfig.QUEUE_USER_REGISTERED,
                event
        );
    }
}
