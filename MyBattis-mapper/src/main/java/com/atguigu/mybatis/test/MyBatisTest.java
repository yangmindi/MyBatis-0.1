package com.atguigu.mybatis.test;


import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;


public class MyBatisTest {

    /**
     * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "com.atguigu.mybatis.dao/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        //sql的唯一标识
        //执行sql要用的参数
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            Employee employee = openSession.selectOne("atguigu.mybatis.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test01() throws IOException {
        //1.获取sqlSessionFactory对象
        String resource = "com.atguigu.mybatis/dao/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            //3.获取接口的实现类对象
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test02() throws IOException {
        String resourc = "com.atguigu.mybatis/dao/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resourc);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(mapper.getClass());
        System.out.println(empById);

    }

    @Test
    public void test03() throws IOException {
        String reources = "com.atguigu.mybatis/dao/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(reources);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);
    }

    @Test
    public void test04() throws IOException {
        String reources = "com.atguigu.mybatis/dao/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(reources);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //1.获得到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //测试添加
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
//            Employee employee = new Employee(null,"jerry","jerry@atguigu","1");
//            mapper.addEmp(employee);
            //测试修改
            Employee employee = new Employee(1,"jerry","jerry@atguigu","1");
            boolean b = mapper.updateEmp(employee);
            System.out.println(b);
            //测试删除
//            mapper.deleteEmpById(2);
            //2.手动提交
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
}

