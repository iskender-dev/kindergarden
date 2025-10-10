package com.example.kindergarden.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "children")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "groupChildren")
@EqualsAndHashCode(of = {"id", "firstName", "lastName"})
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String patronymic;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GroupChildren> groupChildren;

    public Child(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void addGroupChild(GroupChildren groupChild) {
        groupChildren.add(groupChild);
        groupChild.setChild(this);
    }

    public void removeGroupChild(GroupChildren groupChild) {
        groupChildren.remove(groupChild);
        groupChild.setChild(null);
    }
}
