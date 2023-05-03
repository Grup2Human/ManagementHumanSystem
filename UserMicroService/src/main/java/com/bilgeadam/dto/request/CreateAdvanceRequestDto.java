package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@NotBlank
public class CreateAdvanceRequestDto {
    @NotBlank(message = "Manager id boş geçilemez")
    private Long companyManagerId;
    private LocalDate demandDate;
    @NotBlank(message = "Advance amount boş geçilemez")
    private Double advanceAmount;
    private String currency;
    private String demandType;
    @Lob
    private String description;

}
