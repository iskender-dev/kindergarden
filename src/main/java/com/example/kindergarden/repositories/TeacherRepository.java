package com.example.kindergarden.repositories;

import com.example.kindergarden.models.Teacher;
import com.example.kindergarden.models.enums.TeacherDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByFirstNameAndLastNameAndPatronymicAndTeacherDegree(
            String firstName, String lastName, String patronymic, TeacherDegree teacherDegree);
}
