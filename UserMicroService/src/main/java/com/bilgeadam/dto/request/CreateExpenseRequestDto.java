package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@NotBlank
public class CreateExpenseRequestDto {
    @NotBlank(message = "Manager id boş geçilemez")
    private Long companyManagerId;
    private String expenseType;
    private LocalDate demandDate;
    private String expenseAmount;
    private String currency;
    private String document;

}
