package com.accountable.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


/**
 * Don't need userClass join column
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = UserOrgMapping.TABLE_NAME)
public class UserOrgMapping extends AbstractUuidEntity {
  public static final String TABLE_NAME = "user_org_mapping";

  @NotNull
  @Column(name = "user_id")
  private UUID userId;

  @NotNull
  @Column(name = "org_id")
  private UUID orgId;

  @Column(name = "is_approved")
  private Boolean isApproved = false;
}
