package org.example.service;

import org.example.dao.impl.SubjectDao;
import org.example.model.Course;
import org.example.model.Group;
import org.example.model.Subject;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SubjectService {
    private final SubjectDao subjectDao;

    public SubjectService() {
        subjectDao = new SubjectDao();
    }
    public List<Subject> findByCourseAndGroupAndDateAndIsNumerator_NOW(Course course, Group group, LocalDateTime localDateTime, boolean isNumerator) {
        return subjectDao.findByCourseAndGroupAndDateAndIsNumerator_NOW(course, group, localDateTime, isNumerator);
    }
    public List<Subject> findByCourseAndGroupAndDateAndIsNumerator_Day(Course course, Group group, LocalDateTime localDateTime, boolean isNumerator) {
        return subjectDao.findByCourseAndGroupAndDateAndIsNumerator_Day(course, group, localDateTime, isNumerator);
    }
}
