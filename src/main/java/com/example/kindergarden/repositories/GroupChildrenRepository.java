package com.example.kindergarden.repositories;

import com.example.kindergarden.models.GroupChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

public interface GroupChildrenRepository extends JpaRepository<GroupChildren,Long> {
    GroupChildren findByChildIdAndEndDateIsNull(Long id);

    long countByGroupIdAndEndDateIsNull(Long id);

    @Query("SELECT gc FROM GroupChildren gc WHERE gc.child.id = :childId " +
            "AND gc.startDate <= :endDate " +
            "AND (gc.endDate IS NULL OR gc.endDate >= :startDate)")
    GroupChildren findByChildIdAndPeriodOverlap(
            @Param("childId") Long childId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

//    long countByChildIdAndEndDateIsNull(Long childId, LocalDate endDate);
//
//    long countByChildIdAndEndDateIsNull(Long childId);
    boolean existsByChild_FirstNameAndChild_LastNameAndEndDateIsNull(String firstName, String lastName);

    boolean existsByGroup_IdAndEndDateIsNull(Long groupId, LocalDate endDate);
}
