package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class AddPersonnelRequestDto {
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
    @Builder.Default
    private EStatus status=EStatus.PENDING;
}
