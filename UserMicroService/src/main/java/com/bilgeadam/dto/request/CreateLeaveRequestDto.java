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
public class CreateLeaveRequestDto {
    @NotBlank(message = "Manager id boş geçilemez")
    private Long companyManagerId;
    private String leaveType;
    private LocalDate demandDate;
    private LocalDate startDate;
    private LocalDate endDate;

}
