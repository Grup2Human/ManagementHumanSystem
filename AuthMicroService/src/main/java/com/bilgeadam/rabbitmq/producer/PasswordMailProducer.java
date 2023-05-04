package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreatePersonMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordMailProducer {
    // daha once .yml'a ekledigimiz degerleri cekiyoruz:
    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.repasswordmailkey}")
    private String repasswordMailBindingKey;

    /**   Dışarıdan aldıgımız veriyi template uzerinden kuyruga verecegiz: */
    private final RabbitTemplate rabbitTemplate;

    /**   Kuyruga ekleyecegim veriyi gonderiyoruz:  */
    public void sendRepassword(CreatePersonMailModel model){
        rabbitTemplate.convertAndSend(directExchange,repasswordMailBindingKey,model);
    }
}
