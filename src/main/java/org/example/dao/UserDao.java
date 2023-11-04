package org.example.dao;

import org.example.model.User;

public interface UserDao {
    public void saveOrUpdate(Long telegramId, int courseNumber, int groupNumber);
    User findByTelegramId(long telegram_id);
    void save(User user);
    void update(User user);
    void delete(User user);
}
