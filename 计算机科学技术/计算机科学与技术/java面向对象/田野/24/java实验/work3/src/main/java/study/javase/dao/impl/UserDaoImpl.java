package study.javase.dao.impl;

import study.javase.dao.UserDao;
import study.javase.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    // 模拟数据库这里我们硬编码一些用户数据
    private static Map<Integer, User> userMap = new HashMap<>();

    static {
        userMap.put(1, new User(1, "Alice", "a123456",12334));
        userMap.put(2, new User(2, "Bob", "Bob@2023",1234213));
    }

    @Override
    public User getUserByPassword(String password) {
        for (User user : userMap.values()) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        if (userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
        } else {
            System.out.println("用户不存在，无法更新");
        }
    }
}