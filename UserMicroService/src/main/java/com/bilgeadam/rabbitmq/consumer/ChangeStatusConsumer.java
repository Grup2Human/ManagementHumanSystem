package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.ChangeStatusModel;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // console a log info çıktısı vermek için kullanılan kütüphane
public class ChangeStatusConsumer {
    private final AdminService adminService;
    @RabbitListener(queues = ("${rabbitmq.changestatusqueue}"))
    public void ChangeUserStatus(ChangeStatusModel model){
        log.info("User {}",model.toString());
        adminService.changeUserStatus(model);
        //userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model)); 2. tercih
    }
}
