package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.service.AdminService;
import com.bilgeadam.service.CompanyManagerService;
import com.bilgeadam.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // console a log info çıktısı vermek için kullanılan kütüphane
public class AuthIdConsumer {
    private final PersonnelService personnelService;
    private final CompanyManagerService companyManagerService;

    @RabbitListener(queues = ("${rabbitmq.queueRegister}"))
    public void newUserCreate(RegisterModel model){
        log.info("User {}",model.toString());
        //personnelService.createAdmin(model);
        //userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model)); 2. tercih
    }
}
