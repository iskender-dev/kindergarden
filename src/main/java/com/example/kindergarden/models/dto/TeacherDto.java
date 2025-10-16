package com.example.kindergarden.models.dto;

import com.example.kindergarden.models.enums.TeacherDegree;
import lombok.Data;

@Data
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private TeacherDegree teacherDegree;
    private Boolean active;
}
