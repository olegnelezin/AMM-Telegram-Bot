package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private int number;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "courses_groups",
            joinColumns = {@JoinColumn(name="courses_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name="groups_id", referencedColumnName = "id")
    )
    private List<Group> groups;

    public Course(CourseNumber number) {
        this.number = number.getNumber();
    }
    public Course() {}

    @Override
    public String toString() {
        return "Course(id=" + id + ", number=" + number + ", groups=" + groups.toString() + ")";
    }
}
