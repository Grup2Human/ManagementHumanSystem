package com.bilgeadam.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queuepasswordmail}")
    private String queuePasswordMail;  // (kuyruk ismi) bu kuyruga baglayacagiz

    /**  mail için queue oluşturalım:  */
    @Bean
    Queue passwordMailQueue(){
        return new Queue(queuePasswordMail);
    }

    @Value("${rabbitmq.queuerepasswordmail}")
    private String queueRepasswordMail;  // (kuyruk ismi) bu kuyruga baglayacagiz

    /**  mail için queue oluşturalım:  */
    @Bean
    Queue repasswordMailQueue(){
        return new Queue(queueRepasswordMail);
    }

}
