package study.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import study.mybatis.mapper.Mapper;

import java.io.Reader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            Mapper mapper = session.getMapper(Mapper.class);

            mapper.createTableIfNotExists();

            User u1 = new User(null, "Alice", 20);
            int rows = mapper.insert(u1);
            System.out.println("Inserted rows: " + rows + ", new id=" + u1.getId());

            User fetched = mapper.selectById(u1.getId());
            System.out.println("Fetched: " + fetched);

            fetched.setAge(21);
            fetched.setName("Alice Zhang");
            int upd = mapper.update(fetched);
            System.out.println("Updated rows: " + upd);

            List<User> all = mapper.selectAll();
            System.out.println("All users: " + all);

//            int del = mapper.delete(fetched.getId());
//            System.out.println("Deleted rows: " + del);

            all = mapper.selectAll();
            System.out.println("After delete, all users: " + all);
        }
    }
}
