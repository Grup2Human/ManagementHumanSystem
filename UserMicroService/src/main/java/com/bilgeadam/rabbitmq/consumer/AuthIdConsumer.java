package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.AddAuthIdModel;
import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.repository.entity.CompanyManager;
import com.bilgeadam.repository.enums.ERole;
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

    @RabbitListener(queues = ("${rabbitmq.authidqueue}"))
    public void newAuthId (AddAuthIdModel model){
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-");
        log.info("User {}",model.toString());
        if (model.getERole().equals(ERole.COMPANYMANAGER))
            companyManagerService.createAuthId(model);
        else if (model.getERole().equals(ERole.PERSONNEL)) {
            personnelService.createAuthId(model);
        }
        //////
        //personnelService.createAdmin(model);
        //userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model)); 2. tercih
    }
}
