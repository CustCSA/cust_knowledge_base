package com.web.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Mybaits_Utils {
    
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        try {
            // 使用MyBatis提供的Resources类加载mybatis-config.xml文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 构建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 获取SqlSession连接
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}