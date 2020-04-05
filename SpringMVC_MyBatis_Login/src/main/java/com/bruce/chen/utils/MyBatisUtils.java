package com.bruce.chen.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SqlSession openSqlSession() {
        //默认SqlSession会自动提交事务数据
        //设置false代表关闭自动提交，改为手动提交事务数据
        return sqlSessionFactory.openSession(false);
    }

    public static boolean closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
            return true;
        }
        return false;
    }

}
