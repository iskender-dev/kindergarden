package com.example.kindergarden.models.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EnrollChildDto {
    @NotBlank(message = "Имя обязательно")
    String firstName;

    @NotBlank(message = "Фамилия обязательна")
    String lastName;

    String patronymic;

    @NotNull(message = "Дата рождения обязательна")
    LocalDate dateOfBirth;

    @NotNull(message = "ID группы обязателен")
    Long groupId;

    Integer price;

}

