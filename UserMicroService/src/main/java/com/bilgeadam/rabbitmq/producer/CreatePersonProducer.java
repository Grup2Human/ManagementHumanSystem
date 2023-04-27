package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePersonProducer {
    @Value("${rabbitmq.exchange-user}")
    private String directExchange;
    @Value("${rabbitmq.createpersonkey}")
    private String createPersonBindingKey;

    private final RabbitTemplate rabbitTemplate;
    public void sendNewPerson(CreatePersonModel model) {
        rabbitTemplate.convertAndSend(directExchange,createPersonBindingKey,model);
    }
}
