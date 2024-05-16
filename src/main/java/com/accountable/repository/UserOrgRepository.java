package com.accountable.repository;

import com.accountable.entity.UserOrgMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserOrgRepository extends JpaRepository<UserOrgMapping, UUID> {

}
