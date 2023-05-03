package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@SuperBuilder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@ToString // sınıf için toString methodunu tanımlar
@Entity
@Table(name = "tblpersonnel")
public class Personnel extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private LocalDate startDate;
    private LocalDate terminationDate;
    private String profession;
    private String department;
    private Long companyId;

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * AKTIF durumu detaylı anlatılması istenilecek !!!
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PENDING;
    private Double salary;
}
