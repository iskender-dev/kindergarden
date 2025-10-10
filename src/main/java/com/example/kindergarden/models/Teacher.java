package com.example.kindergarden.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.kindergarden.models.enums.TeacherDegree;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"teacherGroups", "nannyGroups"})
@EqualsAndHashCode(of = {"id", "firstName", "lastName"})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(name = "middle_name")
    private String patronymic;

    @Column(nullable = false)
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree", nullable = false)
    private TeacherDegree degree;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Group> teacherGroups;

    @JsonIgnore
    @OneToMany(mappedBy = "nanny", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Group> nannyGroups;

    public Teacher(String firstName, String lastName, TeacherDegree degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
        this.active = true;
    }

    public void addTeacherGroup(Group group) {
        teacherGroups.add(group);
        group.setTeacher(this);
    }

    public void addNannyGroup(Group group) {
        nannyGroups.add(group);
        group.setNanny(this);
    }

    public void deactivate() {
        this.active = false;
    }
}
