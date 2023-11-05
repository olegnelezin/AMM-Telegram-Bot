package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name="subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    @Column(nullable = false)
    private String weekday;

    @Column(nullable = false)
    private LocalTime beginTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int numeratorOrDenominator;

    @Column(nullable = false)
    private String lecturer;

    @Column(nullable = false)
    private String info;

    public Subject() { }
}
