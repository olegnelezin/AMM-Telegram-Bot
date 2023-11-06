package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.model.Course;
import org.example.model.Group;
import org.example.model.User;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoImpl implements UserDao {

    public void saveOrUpdate(Long telegramId, int courseNumber, int groupNumber, int subgroupNumber) {
        CourseDao courseDao = new CourseDao();
        GroupDao groupDao = new GroupDao();
        Course course = courseDao.findByNumber(courseNumber);
        Group group = groupDao.findByNumber(groupNumber);
        User user = findByTelegramId(telegramId);
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if(user == null) {
            user = new User(telegramId, course, group, subgroupNumber);
            session.persist(user);
        } else {
            session.evict(user);
            user.setCourse(course);
            user.setGroup(group);
            user.setSubgroup(subgroupNumber);
            session.merge(user);
        }
        tr.commit();
        session.close();
    }

    @Override
    public User findByTelegramId(long telegram_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("from User where telegramId = :paramId", User.class);
        query.setParameter("paramId", telegram_id);
        User user = query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }
}
