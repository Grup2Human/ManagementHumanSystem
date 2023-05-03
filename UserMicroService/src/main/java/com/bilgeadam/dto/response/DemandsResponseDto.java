package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ELeaveApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class DemandsResponseDto {
    private Long personnelId;
    //private Long companyManagerId;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ELeaveApprovalStatus eLeaveApprovalStatus = ELeaveApprovalStatus.PENDINGAPPROVAL;
    private String name;
    private String surname;
}
