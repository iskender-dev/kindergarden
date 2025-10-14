package com.example.kindergarden.models.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollChildDto {

    @NotBlank(message = "Поле 'Имя' не должно быть пустым")
    private String firstName;

    @NotBlank(message = "Поле 'Фамилия' не должно быть пустым")
    private String lastName;

    private String middleName;

    @NotNull(message = "Дата рождения обязательна для заполнения")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @NotNull(message = "Необходимо указать идентификатор группы")
    @Positive(message = "ID группы должен быть положительным числом")
    private Long groupId;

    @PositiveOrZero(message = "Цена не может быть отрицательной")
    private Integer price;
}

