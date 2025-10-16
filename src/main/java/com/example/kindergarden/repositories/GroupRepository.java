package com.example.kindergarden.repositories;

import com.example.kindergarden.models.Group;
import com.example.kindergarden.models.GroupCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    boolean existsByName(String name);
    boolean existsByGroupCategory_Id(Long groupCategoryId);
    boolean existsByTeacher_Id(Long teacherId);
    boolean existsByNanny_Id(Long nannyId);
}
