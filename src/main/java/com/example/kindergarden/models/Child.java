package com.example.kindergarden.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "children")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Child {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateOfBirth;
}
