package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ELeaveApprovalStatus;
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
@Table(name = "tbladvance")
public class Advance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long personnelId;
    private Long companyManagerId;
    private LocalDate demandDate;
    private LocalDate responseDate;
    private String advanceAmount;
    private String currency;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ELeaveApprovalStatus eLeaveApprovalStatus = ELeaveApprovalStatus.PENDINGAPPROVAL;
    private String demandType;
    @Lob
    private String description;
}
