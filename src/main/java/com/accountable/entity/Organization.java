package com.accountable.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = Organization.TABLE_NAME)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends AbstractUuidEntity {
  public static final String TABLE_NAME = "organizations";

  @Column(name = "org_name")
  @NotNull
  private String orgName;

  @Column(name = "org_code")
  private String code;

  @Column(name = "admin_user_id")
  private UUID adminUser;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "contact_name")
  private String contactName;

  @Column(name = "contact_phone_number")
  private String contactPhoneNumber;

  // Image of the school
  @Column(name = "logo_url")
  private String orgLogo;
}
