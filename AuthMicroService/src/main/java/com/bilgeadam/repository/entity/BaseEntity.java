package com.bilgeadam.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@SuperBuilder // BaseEntity miras alındığı için bunu kullanıyoruz.
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
@MappedSuperclass
public class BaseEntity {
      Long createat;
      Long updateat;
      boolean state;
}
