package com.example.kindergarden.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "group_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupCategory {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Integer price;
}
