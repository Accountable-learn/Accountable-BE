package com.accountable.repository;

import com.accountable.entity.User;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  User findUserByUserIdAndIsActiveTrue(UUID id);

  List<User> findAllByOrgIdAndIsActiveTrue(UUID orgId);
}
