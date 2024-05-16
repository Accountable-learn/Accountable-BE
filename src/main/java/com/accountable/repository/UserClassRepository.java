package com.accountable.repository;

import com.accountable.entity.UserClassMapping;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClassRepository extends JpaRepository<UserClassMapping, UUID> {
  List<UserClassMapping> findAllByClassroomIdAndIsActiveTrue(UUID classroomId);

  List<UserClassMapping> findAllByClassroomIdAndIsApprovedFalseAndIsActiveTrue(UUID classroomId);
}
