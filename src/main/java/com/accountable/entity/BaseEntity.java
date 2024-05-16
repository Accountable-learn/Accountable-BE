package com.accountable.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
  @JsonIgnore
  @Column(name = "is_active")
  Boolean isActive;

  @JsonIgnore
  @Column(name = "created_at", updatable = false)
  OffsetDateTime createdAt;

  @JsonIgnore
  @Column(name = "last_modified")
  OffsetDateTime lastModified;

  @PrePersist
  protected void onCreate() {
    OffsetDateTime now = OffsetDateTime.now();
    this.createdAt = now;
    this.lastModified = now;
    if (null == this.isActive) {
      this.isActive = Boolean.TRUE;
    }
  }

  @PreUpdate
  protected void onUpdate() {
    this.lastModified = OffsetDateTime.now();
  }
}
