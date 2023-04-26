package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
@Slf4j // console a log info çıktısı vermek için kullanılan kütüphane
public class RegisterConsumer{
    private final AdminService adminService;
    @RabbitListener(queues = ("${rabbitmq.queueRegister}"))
    public void newUserCreate(RegisterModel model){
        log.info("User {}",model.toString());
        adminService.createAdmin(model);
        //userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model)); 2. tercih
    }
}
