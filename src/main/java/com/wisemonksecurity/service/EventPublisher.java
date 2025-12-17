package com.wisemonksecurity.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.wisemonksecurity.config.RabbitMQConfig;
import com.wisemonksecurity.event.dto.UserLoginEvent;
import com.wisemonksecurity.event.dto.UserRegisteredEvent;

@Service
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserRegistered(UserRegisteredEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "user.registered",
                event);
    }

    public void publishUserLogin(UserLoginEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "user.login",
                event
        );
    }
}
