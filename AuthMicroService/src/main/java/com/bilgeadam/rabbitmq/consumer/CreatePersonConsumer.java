package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // console a log info çıktısı vermek için kullanılan kütüphane
public class CreatePersonConsumer {
    private final AuthService authService;
    @RabbitListener(queues = ("${rabbitmq.queuecreateperson}"))
    public void newPersonCreate(CreatePersonModel model){
        log.info("User {}",model.toString());
        authService.createPerson(model);
        //userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model)); 2. tercih
    }
}
