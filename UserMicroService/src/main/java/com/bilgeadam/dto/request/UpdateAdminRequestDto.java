package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class UpdateAdminRequestDto {
    private String token;
    private String name;
    private String nameSecond;
    private String surname;
    private String surnameSecond;
    private String phoneNumber;
    private String photo;
    private String address;
    private String birthday;
    private String birthPlace;
    private Long TC;
}
