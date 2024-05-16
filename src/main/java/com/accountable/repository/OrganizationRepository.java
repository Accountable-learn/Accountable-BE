package com.accountable.repository;

import com.accountable.entity.Organization;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
  Organization getOrganizationByIdAndIsActiveTrue(UUID orgId);

  List<Organization> getAllByCodeAndIsActiveTrue(String code);
}
