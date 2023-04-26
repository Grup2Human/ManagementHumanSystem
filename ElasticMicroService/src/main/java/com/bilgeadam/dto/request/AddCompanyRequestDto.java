package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class AddCompanyRequestDto {
    private Long id;
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
