package org.example.dao.impl;

import org.example.dao.AbstractDao;
import org.example.model.Course;
import org.example.model.Group;
import org.example.model.Subject;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SubjectDao extends AbstractDao<Subject, Long> {
    public SubjectDao() {
        super(Subject.class);
    }

    public List<Subject> findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_NOW(Course course, Group group, int subgroup, LocalDateTime localDateTime, boolean isNumerator) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Subject> query = session.createQuery("from Subject where course=:paramCourse AND group=:paramGroup " +
                "AND (subgroup= :paramSubgroup OR subgroup=0) AND (numeratorOrDenominator=0 OR numeratorOrDenominator=:paramNumeratorOrDenominator) " +
                "AND weekday= :paramWeekDay AND :paramLocalTime BETWEEN beginTime AND endTime", Subject.class);
        LocalTime localTime = localDateTime.toLocalTime();
        String weekday = localDateTime.getDayOfWeek().name();
        query.setParameter("paramCourse", course);
        query.setParameter("paramGroup", group);
        query.setParameter("paramSubgroup", subgroup);
        query.setParameter("paramNumeratorOrDenominator", isNumerator ? 1 : -1);
        query.setParameter("paramLocalTime", localTime);
        query.setParameter("paramWeekDay", weekday);
        List<Subject> subjects = query.list();
        session.close();
        return subjects;
    }

    public List<Subject> findByCourseAndGroupAndSubgroupAndDateAndIsNumerator_Day(Course course, Group group, int subgroup, LocalDateTime localDateTime, boolean isNumerator) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Subject> query = session.createQuery("from Subject where course=:paramCourse AND group=:paramGroup " +
                "AND (subgroup= :paramSubgroup OR subgroup=0) AND (numeratorOrDenominator=0 OR numeratorOrDenominator=:paramNumeratorOrDenominator)" +
                "AND weekday= :paramWeekDay order by beginTime", Subject.class);
        String weekday = localDateTime.getDayOfWeek().name();
        query.setParameter("paramCourse", course);
        query.setParameter("paramGroup", group);
        query.setParameter("paramSubgroup", subgroup);
        query.setParameter("paramNumeratorOrDenominator", isNumerator ? 1 : -1);
        query.setParameter("paramWeekDay", weekday);
        List<Subject> subjects = query.list();
        session.close();
        return subjects;
    }
}
