package study.javase.service;

import study.javase.dao.impl.UserDaoImpl;
import study.javase.dao.UserDao;
import study.javase.model.User;

public class UserService {
    private UserDao userDao = new UserDaoImpl();

    public User login(String password) {
        User user = userDao.getUserByPassword(password);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
