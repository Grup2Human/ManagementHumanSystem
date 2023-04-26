package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@SuperBuilder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@ToString // sınıf için toString methodunu tanımlar
@Document(indexName = "tblcompanymanager")
public class CompanyManager extends Person{
        @Id
        private String id;
        private Long companyManagerId;
        private Long authId;
        private LocalDate startDate;
        private String profession;
        private String department;
        private Long companyId;
        @Builder.Default
        private EStatus status=EStatus.PENDING;
}
