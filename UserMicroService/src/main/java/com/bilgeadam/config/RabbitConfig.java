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
    @Value("${rabbitmq.queueRegister}")
    private String queueNameRegister;
    @Bean
    Queue registerQueue() {
        return new Queue(queueNameRegister);
    }
    @Value("${rabbitmq.changestatusqueue}")
    private String changeStatusQueue;
    @Bean
    Queue changeStatusQueue() {
        return new Queue(changeStatusQueue);
    }

    // kuyruk oluşturmak için 3 şey lazım: kuyruk ismi, exchange(tüm kuyruklarda kullanılabilir), bindingKey
    // bu degerleri .yml'dan çekiyoruz:
    @Value("${rabbitmq.exchange-user}")
    private String exchange;  // bu exchange uzerinden...

    // mail için kuyruk parametreleri oluşturuyoruz:
    @Value("${rabbitmq.createpersonkey}")
    private String createPersonBindingKey; // bu key'i kullanarak
    @Value("${rabbitmq.queuecreateperson}")
    private String queueCreatePerson;  // (kuyruk ismi) bu kuyruga baglayacagiz


    /**  Exchange oluşturalım:  */
    @Bean // singleton yapıda, altta donen DirectExchange'i yonetmek için bean ekliyoruz
    DirectExchange exchangePerson(){
        return new DirectExchange(exchange);
    }

    /**  mail için queue oluşturalım:  */
    @Bean
    Queue createPersonQueue(){
        return new Queue(queueCreatePerson);
    }

    /**  mail kuyrugu icin binding olusturalım:  */
    @Bean
    public Binding bindingCreatePerson(final Queue createPersonQueue, final DirectExchange exchangePerson){
        return BindingBuilder.bind(createPersonQueue).to(exchangePerson).with(createPersonBindingKey);
    }
}
