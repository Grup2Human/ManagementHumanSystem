package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeStatusProducer {
    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.changestatusbindingkey}")
    private String changeStatusBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void changeStatusUser(ChangeStatusModel model) {
        rabbitTemplate.convertAndSend(directExchange, changeStatusBindingKey, model);
    }
}
