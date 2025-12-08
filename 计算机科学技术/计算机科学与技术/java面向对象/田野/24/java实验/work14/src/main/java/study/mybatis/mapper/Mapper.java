package study.mybatis.mapper;

import study.mybatis.User;
import java.util.List;

public interface Mapper {
    // Initialize schema
    int createTableIfNotExists();

    int insert(User user);
    int update(User user);
    int delete(int id);
    User selectById(int id);
    List<User> selectAll();
}
