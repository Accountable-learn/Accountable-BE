package com.accountable.repository;

import com.accountable.entity.Classroom;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
  Classroom getClassroomByIdAndIsActiveTrue(UUID classroomId);

  @Query(
      nativeQuery = true,
      value =
          """
            SELECT
                c.id,
                c.org_id,
                c.class_name,
                c.is_active,
                c.code,
                c.created_at,
                c.last_modified
            FROM classrooms c
            JOIN user_class_mapping ucm ON c.id = ucm.classroom_id
            WHERE ucm.user_id = :userId
        """)
  List<Classroom> getClassroomsByUserId(UUID userId);
}
