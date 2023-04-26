package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.EStatus;
import lombok.*;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class AddAdminRequestDto {
    private Long id;
    private Long authId;
}
