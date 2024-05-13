package com.accountable.controller;


import com.accountable.entity.Question;
import com.accountable.entity.UserOrgMapping;
import com.accountable.response.AbstractResponse;
import com.accountable.response.CustomResponse;
import com.accountable.service.QuestionService;
import com.accountable.service.UserOrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/org/{orgId}/")
public class UserOrgController extends AbstractResponse {

    public final UserOrgService userOrgService;
    @PostMapping(path = "request/org")
    //    @PreAuthorize("hasAnyAuthority('teacher:create')")
    public ResponseEntity<CustomResponse> createJoinRequest(@RequestBody UserOrgMapping mapping) {
        return okResponseEntity("Request created successfully", userOrgService.createJoinRequest(mapping));
    }
}
