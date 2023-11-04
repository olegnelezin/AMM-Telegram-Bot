package org.example.model;

public enum CourseNumber {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    FIRST_MAGISTRACY(6),
    SECOND_MAGISTRACY(7),
    POSTGRADUATE_STUDY(8);

    private final int number;

    CourseNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
