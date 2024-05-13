package com.accountable.repository;

import com.accountable.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  User findUserByUserIdAndIsActiveTrue(UUID id);

  List<User> findAllByOrganizationIdAndIsActiveTrue(UUID orgId);

  @Query(
      nativeQuery = true,
      value =
          """
      SELECT u.*
      FROM users u
      JOIN user_class_mapping ucm ON u.user_id = ucm.user_id
      JOIN classrooms c ON ucm.classroom_id = c.id
      WHERE c.id = :classroomId
      AND u.role = :role;
    """)
  List<User> getClassroomsByUserIdAndRole(UUID classroomId, String role);
}
