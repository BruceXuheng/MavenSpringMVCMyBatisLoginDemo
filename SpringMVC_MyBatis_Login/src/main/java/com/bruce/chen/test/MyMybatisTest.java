package com.bruce.chen.test;

import com.bruce.chen.entity.SpringmvcUser;
import com.bruce.chen.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMybatisTest {

    @Test
    public void testSqlSessionFactory() throws IOException {
        //读取配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

        //初始化SqlSessionFactory,同事解析mybatis-config.xml
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        System.out.println("加载成功");

        //创建SqlSession对象，SqlSession是JDBC扩展类
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //mybatis-config.xml配置的type不同 close也不同
                //type="POOLED"  close则将回收到连接池中
                //typr="UNPOOLED" close调用底层Connection。close关闭连接
                sqlSession.close();
            }
        }

    }

    @Test
    public void testSession() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            Connection connection = sqlSession.getConnection();
            System.out.println("connection=" + connection);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSqlSession(sqlSession);
            }
        }

    }


    @Test
    public void testSelectAllSession() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            Connection connection = sqlSession.getConnection();
            List<SpringmvcUser> datas = sqlSession.selectList("springmvcUser.findAll");
            for (SpringmvcUser data : datas) {
                System.out.println("datas=" + data.toString());
            }
            System.out.println("connection=" + connection);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSqlSession(sqlSession);
            }
        }


    }

    @Test
    public void testSelectByName(){
        SqlSession sqlSession=null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            SpringmvcUser springmvcUser = sqlSession.selectOne("springmvcUser.findByName","88");
            System.out.println(springmvcUser);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testSelectByIdBetween(){
        SqlSession sqlSession=null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            Map<String, String> map = new HashMap<String,String>();
            map.put("min","0");
            map.put("max","2");
            List<SpringmvcUser> springmvcUser = sqlSession.selectList("springmvcUser.findById",map);
            for (SpringmvcUser user : springmvcUser) {
                System.out.println(user.toString());

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession=null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            SpringmvcUser user = new SpringmvcUser("88","88");
            int num = sqlSession.insert("springmvcUser.insertUser",user);
            sqlSession.commit();
            System.out.println(num+"  id="+user.getId());
        }catch (Exception e){
            if(sqlSession!=null){
                sqlSession.rollback();
            }
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }


    @Test
    public void updateUser(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSqlSession();
            System.out.println("-------------");
            SpringmvcUser user = sqlSession.selectOne("springmvcUser.findById",2);
            System.out.println(user.toString());
            user.setUpwd("1234567890");
            int num =  sqlSession.update("springmvcUser.update",user);
            System.out.println(num);
            System.out.println("-------");
            sqlSession.commit();
        }catch (Exception e){
            sqlSession.rollback();

        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }

}
