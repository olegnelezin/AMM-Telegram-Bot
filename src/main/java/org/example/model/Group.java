package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private int number;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> courses;

    public Group(int number) {
        this.number = number;
    }
    public Group() {}

    @Override
    public String toString() {
        return "Group(id=" + id + ", number=" + number + ")";
    }
}
