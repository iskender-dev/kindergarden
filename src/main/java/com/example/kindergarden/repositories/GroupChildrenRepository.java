package com.example.kindergarden.repositories;

import com.example.kindergarden.models.GroupChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupChildrenRepository extends JpaRepository<GroupChildren, Long> {

    long countByGroupIdAndEndDateIsNull(Long groupId);

    boolean existsByChild_FirstNameAndChild_LastNameAndEndDateIsNull(String firstName, String lastName);

    // Исправленный метод
    boolean existsByGroup_IdAndEndDateIsNull(Long groupId);
}

