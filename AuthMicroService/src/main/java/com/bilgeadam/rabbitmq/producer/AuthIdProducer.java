package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthIdProducer {
    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.authidbindingkey}")
    private String authIdBindingKey;

    private final RabbitTemplate rabbitTemplate;
    public void sendNewUser(RegisterModel model) {
        rabbitTemplate.convertAndSend(directExchange,authIdBindingKey,model);
    }
}
