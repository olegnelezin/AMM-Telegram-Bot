package org.example.dao.impl;

import org.example.dao.AbstractDao;
import org.example.model.Course;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CourseDao extends AbstractDao<Course, Long> {
    public CourseDao() {
        super(Course.class);
    }

    public Course findByNumber(int number) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Course> query = session.createQuery("from Course where number = :paramNumber", Course.class);
        query.setParameter("paramNumber", number);
        Course course = query.uniqueResult();
        session.close();
        return course;
    }
}
