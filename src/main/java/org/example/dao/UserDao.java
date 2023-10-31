package org.example.dao;

import org.example.model.User;

public interface UserDao {
    User findByTelegramId(long telegram_id);
    void save(User user);
    void update(User user);
    void delete(User user);
}
