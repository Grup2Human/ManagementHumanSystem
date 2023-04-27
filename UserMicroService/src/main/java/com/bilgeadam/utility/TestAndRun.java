package com.bilgeadam.utility;

import com.bilgeadam.manager.IElasticServiceAdminManager;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestAndRun {
    private final AdminService adminService;
    private final IElasticServiceAdminManager elasticServiceAdminManager;
    @PostConstruct
    public void init() {
        /**
         * Bu kısım kullanılacak ise, zorunlu durumlar için işiniz bitince
         * bu kodu yorum satırına almak doğru olacaktır.
         * çalışması sistemi etkilemeyen durumlarda thread içinde çalıştırmak doğru olacaktır.
         */

 //           runAdmin();
//        new Thread(()->{
//            runAdmin();
//        });
//        new Thread(()->{
//            runAdmin();
//        });
//        new Thread(()->{
//            runAdmin();
//        });
//        new Thread(()->{
//            runAdmin();
//        });
    }

    public void runAdmin() {
        List<Admin> list = adminService.findAll();
        System.out.println(list.get(0));
        list.forEach(x->{
            elasticServiceAdminManager.addAdmin(x);
            System.out.println(x.toString());
        });
    }
}
