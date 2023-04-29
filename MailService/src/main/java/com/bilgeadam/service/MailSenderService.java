package com.bilgeadam.service;

import com.bilgeadam.rabbitmq.model.CreatePersonMailModel;
import com.bilgeadam.rabbitmq.model.CreatePersonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;
    public void sendMail(CreatePersonMailModel model){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("${java6mailusername}"); // e-mail gonderen adres
        mailMessage.setTo(model.getEmail()); // mail'i hangi adrese gonderecegim
        mailMessage.setSubject("Login Şifresi...."); // mail konusu
        // mail içerigi:
        mailMessage.setText("Başarılı bir şekilde kayıt oldunuz\n"
                +"Login şifreniz: "+model.getPassword());
        javaMailSender.send(mailMessage);
    }
}
