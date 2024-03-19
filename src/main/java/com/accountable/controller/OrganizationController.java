package com.accountable.controller;


import com.accountable.entity.Organization;
import com.accountable.response.CustomResponse;
import com.accountable.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.accountable.entity.Question;
import com.accountable.response.AbstractResponse;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/org")
public class OrganizationController extends AbstractResponse {
    private final OrganizationService orgService;
    @GetMapping(path = "/{orgId}")
    public ResponseEntity<CustomResponse> get(@PathVariable UUID orgId) {
        return okResponseEntity(
                "Org retrieved successfully",
                orgService.get(orgId));
    }

    // TODO: Maybe we shouldn't expose this endpoint?
    @PostMapping(path="/create")
    public ResponseEntity<CustomResponse> getRandQuestion(@RequestBody Organization organization) {
        return okResponseEntity("Org created successfully", orgService.create(organization));
    }

    @PatchMapping("/update")
    public ResponseEntity<CustomResponse> update(@RequestBody Organization organization) {
        return okResponseEntity(
                "Org retrieved successfully",
                orgService.update(organization));
    }

    @GetMapping("/{orgId}/admin/users")
//  TODO: ADD THIS  @PreAuthorize("hasAnyAuthority('admin:get')")
    public ResponseEntity<CustomResponse> listAllUsers(@PathVariable UUID orgId) {
        return okResponseEntity(
                "All users retrieved successfully",
                orgService.listAllUsersByOrgId(orgId));
    }
}
