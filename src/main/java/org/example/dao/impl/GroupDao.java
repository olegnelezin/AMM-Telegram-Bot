package org.example.dao.impl;

import org.example.dao.AbstractDao;
import org.example.model.Group;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class GroupDao extends AbstractDao<Group, Long> {
    public GroupDao() {
        super(Group.class);
    }

    public Group findByNumber(int number) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Group> query = session.createQuery("from Group where number = :paramNumber", Group.class);
        query.setParameter("paramNumber", number);
        Group group = query.uniqueResult();
        session.close();

        return group;
    }
}
