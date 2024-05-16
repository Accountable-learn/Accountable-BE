package com.accountable.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractUuidEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(
      name = "id",
      updatable = false,
      nullable = false,
      columnDefinition = "uuid default uuid_generate_v4()")
  private UUID id;
}
