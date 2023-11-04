package org.example.service;

import org.example.dao.impl.CourseDao;
import org.example.model.Course;
import org.example.model.CourseNumber;

public class CourseService {
    private final CourseDao courseDao;

    public CourseService() {
        this.courseDao = new CourseDao();
    }

    public Course findByNumber(int number) {
        return courseDao.findByNumber(number);
    }
}
