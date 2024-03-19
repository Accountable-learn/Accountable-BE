package com.accountable.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = Organization.TABLE_NAME)
public class Organization extends AbstractUuidEntity {
    public static final String TABLE_NAME = "organizations";

    @Column(name = "org_name")
    @NotNull
    private String orgName;

    @Column(name="org_code")
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
    @Column(name="logo_url")
    private String orgLogo;
}
