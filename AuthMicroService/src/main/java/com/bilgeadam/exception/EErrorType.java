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
    MUSTERI_BULUNAMADI(1003,"Aradığınız müşteri sistemde kayıtlı değildir.", INTERNAL_SERVER_ERROR),
    REGISTER_ERROR_PASSWORD_UNMATCH(1004, "Girilen parolalar uyuşmamaktadır",BAD_REQUEST),
    REGISTER_ERROR_EMAIL(1005,"Bu email daha önce alınmıştır",BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Kullanıcı adı ya da şifre hatalıdır",BAD_REQUEST),
    INVALID_TOKEN(1007,"Geçersiz Token",BAD_REQUEST),
    NOT_ACTIVE_ACCOUNT(1008,"Hesabınız şu anda aktif değildir", BAD_REQUEST),
    LOGIN_ERROR_ADMIN(1009,"Admin girisi gereklidir !",BAD_REQUEST),
    CREATE_ERROR(1010, "Kullanici olusturulamadi",BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır",BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre girişi yaptınız", BAD_REQUEST),
    ACTIVATE_CODE_ERROR(4113,"Aktivasyon kod hatası",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4112,"Böyle bie kullanıcı bulunamadı",HttpStatus.NOT_FOUND);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
