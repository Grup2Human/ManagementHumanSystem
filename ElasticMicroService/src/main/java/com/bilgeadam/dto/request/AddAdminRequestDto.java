package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.EStatus;
import lombok.*;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class AddAdminRequestDto {
    private Long id;
    private Long authId;
    private String name;
    private String nameSecond;
    private String surname;
    private String surnameSecond;
    private String photo;
    private String email;
    private String birthday;
    private String birthPlace;
    private Long TC;
    private String address;
    private String phoneNumber;
    private EStatus status;
}
