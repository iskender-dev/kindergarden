package com.example.kindergarden.models.dto;

import com.example.kindergarden.models.enums.TeacherDegree;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCreateDto {
    @NotBlank(message = "Заполните поле имя оябзательно")
    String firstName;
    @NotBlank(message = "Заполните поле фамилия обязательно")
    String lastName;
    String patronymic;
    @NotNull(message = "Введите роль")
    TeacherDegree teacherDegree;
    @NotNull(message = "Статус обязателен")
    Boolean active;
}
