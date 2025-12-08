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

            // 初始化表结构
            mapper.createTablesIfNotExists();
            mapper.createContactGroupIfNotExists();
            mapper.createContactIfNotExists();

            // 模拟一个账号注册/登录
            AccountUser account = new AccountUser(null, "user1", "123456");
            mapper.insertAccountUser(account);

            // 为该账号添加几个分组
            ContactGroup g1 = new ContactGroup(null, "高中同学");
            mapper.insertGroup(g1);
            ContactGroup g2 = new ContactGroup(null, "大学同学");
            mapper.insertGroup(g2);

            // 为该账号添加几个联系人
            Contact c1 = new Contact(null, account.getId(), g1.getId(), "张三", "13800000001");
            Contact c2 = new Contact(null, account.getId(), g2.getId(), "李四", "13800000002");
            mapper.insertContact(c1);
            mapper.insertContact(c2);

            // 查询并以表格形式显示该账号下的通讯录
            List<Contact> contacts = mapper.selectContactsByUserId(account.getId());
            printContactsTable(contacts);
        }
    }

    private static void printContactsTable(List<Contact> contacts) {
        System.out.println("+----+----------+------------+--------------+");
        System.out.println("| ID | 用户ID   | 分组ID     | 姓名/电话     |");
        System.out.println("+----+----------+------------+--------------+");
        for (Contact c : contacts) {
            System.out.printf("| %-2d | %-8d | %-10d | %s/%s%n",
                    c.getId(), c.getUserId(), c.getGroupId(), c.getName(), c.getPhone());
        }
        System.out.println("+----+----------+------------+--------------+");
    }
}

