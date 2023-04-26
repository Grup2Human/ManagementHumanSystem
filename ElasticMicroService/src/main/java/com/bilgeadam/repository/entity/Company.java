package com.bilgeadam.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@SuperBuilder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@ToString // sınıf için toString methodunu tanımlar
@Document(indexName = "tblcompany")
public class Company extends BaseEntity{
    @Id
    private String id;
    private Long companyId;
    private String companyName;
    private String companyType; // LTD.ŞTİ
    private String centralRegistrationSystemNo; // mersis no
    private String taxId; // vergi no
    private String taxOffice; // vergi dairesi
    private String logo;
    private String phone;
    private String address;
    private String email;
    private String headcount; // çalışan sayısı
    private int foundationYear; //kuruluş yılı
    private LocalDate contractStartDate; // sözleşme başlangıç tarihi
    private LocalDate contractEndDate; // sözleşme bitiş tarihi


}
