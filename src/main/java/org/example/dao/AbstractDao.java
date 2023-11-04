package org.example.dao;

import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDao<E, K>{
    private final Class<E> clazz;

    public AbstractDao(final Class<E> clazzToSet)   {
        this.clazz = clazzToSet;
    }
    public E findById(K id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(clazz, id);
    }
    public void save(E object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
    }
    public void update(E object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();

    }
    public void delete(E object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(object);
        tx1.commit();
        session.close();
    }
}
