package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telegram_id")
    private long telegramId;

    @Column(name = "course")
    private int course;

    @Column(name = "_group")
    private int _group;

    public User(long telegramId, int course, int group) {
        this.telegramId = telegramId;
        this.course = course;
        this._group = group;
    }
}
