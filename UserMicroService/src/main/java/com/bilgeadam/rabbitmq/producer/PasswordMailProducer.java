package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreatePersonMailModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordMailProducer {
    // daha once .yml'a ekledigimiz degerleri cekiyoruz:
    @Value("${rabbitmq.exchange-user}")
    private String directExchange;
    @Value("${rabbitmq.passwordmailkey}")
    private String passwordMailBindingKey;

    /**   Dışarıdan aldıgımız veriyi template uzerinden kuyruga verecegiz: */
    private final RabbitTemplate rabbitTemplate;

    /**   Kuyruga ekleyecegim veriyi gonderiyoruz:  */
    public void sendPassword(CreatePersonMailModel model){
        rabbitTemplate.convertAndSend(directExchange,passwordMailBindingKey,model);
    }
}
