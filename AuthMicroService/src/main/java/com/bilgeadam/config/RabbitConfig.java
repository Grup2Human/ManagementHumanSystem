package com.bilgeadam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.registerkey}")
    private String registerBindingKey;
    @Value("${rabbitmq.queueRegister}")
    private String queueNameRegister;
    @Value("${rabbitmq.changestatusbindingkey}")
    private String changeStatusBindingKey;
    @Value("${rabbitmq.changestatusqueue}")
    private String changeStatusQueue;
    @Value("${rabbitmq.authidbindingkey}")
    private String authIdBindingKey;
    @Value("${rabbitmq.authidqueue}")
    private String authIdQueue;
    @Value("${rabbitmq.repasswordmailkey}")
    private String repasswordMailBindingKey; // bu key'i kullanarak
    @Value("${rabbitmq.queuerepasswordmail}")
    private String queueRepasswordMail;  // (kuyruk ismi) bu kuyruga baglayacagiz

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    Queue registerQueue() {
        return new Queue(queueNameRegister);
    }
    @Bean
    public Binding bindingRegister(final Queue registerQueue, final DirectExchange exchangeAuth) {
        return BindingBuilder.bind(registerQueue).to(exchangeAuth).with(registerBindingKey);
    }
    @Bean
    Queue changeStatusQueue() {
        return new Queue(changeStatusQueue);
    }
    @Bean
    public Binding bindingActivation(final Queue changeStatusQueue, final DirectExchange exchangeAuth) {
        return BindingBuilder.bind(changeStatusQueue).to(exchangeAuth).with(changeStatusBindingKey);
    }

    /**
     * User kayıt olan person
     */
    @Value("${rabbitmq.queuecreateperson}")
    private String queueCreatePerson;  // (kuyruk ismi) bu kuyruga baglayacagiz

    /**  mail için queue oluşturalım:  */
    @Bean
    Queue createPersonQueue(){
        return new Queue(queueCreatePerson);
    }

    @Bean
    Queue authIdQueue() {
        return new Queue(authIdQueue);
    } //Auth Göndermek için Queue Gönderiyoruz
    @Bean
    public Binding bindingSendAuthId(final Queue authIdQueue, final DirectExchange exchangeAuth) {
        return BindingBuilder.bind(authIdQueue).to(exchangeAuth).with(authIdBindingKey);
    }
    @Bean
    Queue repasswordMailQueue(){
        return new Queue(queueRepasswordMail);
    }

    /**  mail kuyrugu icin binding olusturalım:  */
    @Bean
    public Binding bindingRepasswordMail(final Queue repasswordMailQueue, final DirectExchange exchange){
        return BindingBuilder.bind(repasswordMailQueue).to(exchange).with(repasswordMailBindingKey);
    }

}
