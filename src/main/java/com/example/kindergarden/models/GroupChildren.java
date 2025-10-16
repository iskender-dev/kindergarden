package com.example.kindergarden.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groupChildren")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChildren {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Child child;

    @ManyToOne
    private Group group;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer price;
}
