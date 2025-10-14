package com.example.kindergarden.repositories;

import com.example.kindergarden.models.Group;
import com.example.kindergarden.models.GroupCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    Page<Group> findAllByOrderByGroupCategoryName(Pageable pageable);
    boolean existsByGroupCategory(GroupCategory groupCategory);
}
