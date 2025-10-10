package com.example.kindergarden.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "group_category")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "groups")
@EqualsAndHashCode(of = {"id", "name"})
public class GroupCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Integer price;

    @JsonIgnore
    @OneToMany(mappedBy = "groupCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Group> groups;

    public GroupCategory(String name, Integer price) {
        this.name = name;
        this.price = price;
        this.active = true;
    }

    public void addGroup(Group group) {
        groups.add(group);
        group.setGroupCategory(this);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group.setGroupCategory(null);
    }
}