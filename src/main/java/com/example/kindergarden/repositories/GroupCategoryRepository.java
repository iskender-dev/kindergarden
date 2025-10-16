package com.example.kindergarden.repositories;

import com.example.kindergarden.models.GroupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCategoryRepository extends JpaRepository<GroupCategory,Long> {

    boolean existsByName(String name);
}
