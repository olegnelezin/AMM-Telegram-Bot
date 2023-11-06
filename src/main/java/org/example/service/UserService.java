package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.User;


public class UserService {
    private final UserDao userDaoImpl;
    public UserService() {
        userDaoImpl = new UserDaoImpl();
    }

    public void saveOrUpdate(Long telegramId, int courseNumber, int groupNumber, int subgroupNumber) {
        userDaoImpl.saveOrUpdate(telegramId, courseNumber, groupNumber, subgroupNumber);
    }
    public User findUserByTelegramId(long telegram_id) {
        return userDaoImpl.findByTelegramId(telegram_id);
    }

    public void saveUser(User user) {
        userDaoImpl.save(user);
    }

    public void delete(User user) {
        userDaoImpl.delete(user);
    }

    public void update(User user) {
        userDaoImpl.update(user);
    }
}
