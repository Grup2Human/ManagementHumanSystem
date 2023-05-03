package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.EApprovalStatus;
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
public class LeaveDemandsResponseDto {
    private Long personnelId;
    private Long leaveId;
    //private Long companyManagerId;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EApprovalStatus eApprovalStatus = EApprovalStatus.PENDINGAPPROVAL;
    private String name;
    private String surname;
}
