package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;

@SuperBuilder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@ToString // sınıf için toString methodunu tanımlar
@Entity
@Table(name = "tblauth")
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nameSecond;
    private String surname;
    private String surnameSecond;
    private String photo;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    //private String activationCode;
    private String birthday;
    private String birthPlace;
    private Long TC;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PENDING;
}
