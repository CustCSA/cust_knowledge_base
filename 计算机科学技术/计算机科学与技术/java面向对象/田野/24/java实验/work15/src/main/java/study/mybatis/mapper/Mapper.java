package study.mybatis.mapper;

import study.mybatis.AccountUser;
import study.mybatis.Contact;
import study.mybatis.ContactGroup;

import java.util.List;

public interface Mapper {
    // 初始化三张表
    int createTablesIfNotExists();
    int createContactGroupIfNotExists();
    int createContactIfNotExists();

    // 账号用户
    int insertAccountUser(AccountUser user);
    AccountUser selectAccountUserByUsername(String username);

    // 分组
    int insertGroup(ContactGroup group);
    List<ContactGroup> selectGroupsByUserId(int userId);

    // 通讯录联系人
    int insertContact(Contact contact);
    int updateContact(Contact contact);
    int deleteContact(int id);
    List<Contact> selectContactsByUserId(int userId);
}

