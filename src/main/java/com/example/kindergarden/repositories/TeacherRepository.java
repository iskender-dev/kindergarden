package com.example.kindergarden.repositories;

import com.example.kindergarden.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByFristNameAndLastNameAndPatronimycIgnoreCaseAndIdNot(String fristName, String lastName, String patronimyc, Long id);
    boolean existsByFirstNameAndLastNameAndPatronymicIgnoreCase(String firstName, String lastName, String patronymic);
}
