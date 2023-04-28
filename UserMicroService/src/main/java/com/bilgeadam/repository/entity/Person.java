package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;

@SuperBuilder // BaseEntity miras alındığı için bunu kullanıyoruz.
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@MappedSuperclass
public class Person extends BaseEntity{

    private String name;
    private String nameSecond;
    private String surname;
    private String surnameSecond;
    private String photo;
    @Column(unique = true)
    @Email
    private String email;
    private String birthday;
    private String birthPlace;
    private Long TC;
    private String address;
    private String phoneNumber;
}
