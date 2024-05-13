package com.accountable.service;


import com.accountable.entity.Organization;
import com.accountable.entity.User;
import com.accountable.entity.UserOrgMapping;
import com.accountable.exception.ErrorCode;
import com.accountable.exception.GenericException;
import com.accountable.repository.UserOrgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOrgService {
    public final UserOrgRepository userOrgRepo;
    public final OrganizationService orgService;
    public final UserService userService;


    /**
     * Function to create an org join request
     * **/
    public UserOrgMapping createJoinRequest(UserOrgMapping userOrgMapping){
        userOrgMapping.setIsApproved(false);
        Organization organization = orgService.get(userOrgMapping.getOrgId());
        User user = userService.getUserById(userOrgMapping.getUserId());
        if (null != user && null != organization ){
            return userOrgRepo.saveAndFlush(userOrgMapping);
        }else{
            throw new GenericException(ErrorCode.REQUEST_ON_CREATE, "org or user does not exist");
        }
    }
}
