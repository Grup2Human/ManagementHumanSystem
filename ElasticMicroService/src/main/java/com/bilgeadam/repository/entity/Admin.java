package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@SuperBuilder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@ToString // sınıf için toString methodunu tanımlar
@Document(indexName = "tbladmin")
public class Admin extends Person{
    @Id
    String id;
    private Long adminId;
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
    @Builder.Default
    private EStatus status=EStatus.PENDING;
}
