package study.javase.dao;

import study.javase.model.User;

public interface UserDao {
    User getUserByPassword(String password);
    void updateUser(User user);
}