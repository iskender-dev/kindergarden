package com.example.kindergarden.repositories;

import com.example.kindergarden.models.GroupChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GroupChildrenRepository extends JpaRepository<GroupChildren,Long> {
    GroupChildren findByChildIdAndDateIsNull(Long childId);

    long countByChildIdAndDateIsNull(Long childId);

    @Query("SELECT gc FROM GroupChildren gc WHERE gc.child.id = :childId " +
            "AND gc.startDate <= :endDate " +
            "AND (gc.endDate IS NULL OR gc.endDate >= :startDate)")
    GroupChildren findByChildAndPeriodOVerlap(
            @Param("ChildId") Long childId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate
    );

}
