package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@Getter
public enum EErrorType {
    REGISTER_ERROR_EMAIL(1005,"Bu email daha önce alınmıştır",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5200,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatası", HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATE(4210,"Böyle bir email mevcut",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4211,"Böyle bie kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    USER_NOT_CREATED(4212,"Kullanıcı oluşturulamadı",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4213,"Geçersiz Token",HttpStatus.BAD_REQUEST),
    FOLLOW_REQUEST_ALREADY_EXISTS(4214,"Böyle bir takip isteği daha önce oluşturulmuştur",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(4215,"Böyle bir sirket bulunamadı",HttpStatus.NOT_FOUND),
    COMPANY_NOT_CREATED(4216,"Sirket olusturulamadi",HttpStatus.BAD_REQUEST),
    PERSONNEL_NOT_CREATED(4217,"Personel olusturulamadi",HttpStatus.BAD_REQUEST),
    COMPANY_MANAGER_NOT_CREATED(4218,"Yonetici olusturulamadi",HttpStatus.BAD_REQUEST),
    TUR_NOT_FOUND(4219,"Tur seciminiz hatali",HttpStatus.BAD_REQUEST),
    LEAVE_NOT_FOUND(4220,"İzin isteği bulunmamaktadır",HttpStatus.NOT_FOUND),
    ADVANCE_NOT_FOUND(4221,"Avans isteği bulunmamaktadır",HttpStatus.NOT_FOUND),
    EXPENSE_NOT_FOUND(4222,"Harcama isteği bulunmamaktadır",HttpStatus.NOT_FOUND),
    UNAUTHORIZED_REQUEST(4223,"Yetkisiz izin isteği",HttpStatus.BAD_REQUEST),
    MUSTERI_BULUNAMADI(1003,"Aradığınız müşteri sistemde kayıtlı değildir.", INTERNAL_SERVER_ERROR),
    REGISTER_ERROR_PASSWORD_UNMATCH(1004, "Girilen parolalar uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1005,"Bu kullanıcı adı daha önce alınmıştır",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
    URUN_EKLEME(2001,"Ürün ekleme başarısız oldu", INTERNAL_SERVER_ERROR),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre girişi yaptınız", HttpStatus.BAD_REQUEST),
    INVALID_ADVANCE_AMOUNT(3002,"Talep edilen tutar maaşın 3 katını geçemez!", HttpStatus.BAD_REQUEST);



    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
