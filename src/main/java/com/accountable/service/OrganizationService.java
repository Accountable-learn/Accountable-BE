package com.accountable.service;

import com.accountable.entity.Organization;
import com.accountable.entity.User;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.OrganizationRepository;
import com.accountable.repository.UserRepository;
import com.accountable.utilities.EntityUtils;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationService {
  private final OrganizationRepository orgRepo;
  private final UserRepository userRepo;

  public Organization create(Organization organization) {
    Organization newOrg = orgRepo.saveAndFlush(organization);
    if (null == newOrg.getCode()) {
      // Org code is the first 7 digits of UUID (which is not unique)
      newOrg.setCode(newOrg.getId().toString().substring(0, 7));
      orgRepo.saveAndFlush(newOrg);
    }
    return newOrg;
  }

  public Organization get(UUID orgId) {
    return orgRepo.getOrganizationByIdAndIsActiveTrue(orgId);
  }

  public List<Organization> getOrgByCode(String code) {
    if (null == code){
      return null;
    }
    return orgRepo.getAllByCodeAndIsActiveTrue(code);
  }

  public Organization update(Organization organization) {
    UUID orgId = organization.getId();
    Organization existingOrg = get(orgId);
    if (null == existingOrg) {
      throw new GenericException(ErrorCode.ORG_ON_UPDATE, "Org does not exist");
    }

    Organization patchedOrganization = EntityUtils.mergeEntitiesDelta(existingOrg, organization);
    return orgRepo.saveAndFlush(patchedOrganization);
  }

  // TODO: Consider make isActive false to preserve user data instead of hard delete
  public Organization delete(UUID orgId) {
    Organization existingOrg = get(orgId);
    if (null == existingOrg) {
      throw new GenericException(ErrorCode.ORG_ON_UPDATE, "Org does not exist");
    }
    orgRepo.delete(existingOrg);
    return existingOrg;
  }

  // For admin
  public List<User> listAllUsersByOrgId(UUID orgId) {
    return userRepo.findAllByOrganizationIdAndIsActiveTrue(orgId);
  }
}
