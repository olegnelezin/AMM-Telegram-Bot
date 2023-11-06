package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id", unique = true)
    private long telegramId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Group group;

    @Column(nullable = false)
    private int subgroup;

    public User(long telegramId, Course course, Group group, int subgroup) {
        this.telegramId = telegramId;
        this.course = course;
        this.group = group;
        this.subgroup = subgroup;
    }

    public User() {}
}
