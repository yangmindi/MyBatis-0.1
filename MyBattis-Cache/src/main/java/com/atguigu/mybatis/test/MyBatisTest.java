package com.atguigu.mybatis.test;


import com.atguigu.mybatis.bean.Department;
import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MyBatisTest {

    @Test
    public void testBatchSave() throws IOException {
        String resources = "com.atguigu.mybatis.dao/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resources);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<Employee>();
            emps.add(new Employee(null, "smith", "simth@atguigu.com", "1"));
            emps.add(new Employee(null, "allen", "allen@atguigu.com", "0"));
            mapper.addEmps(emps);
            openSession.commit();

//            EmployeeMapperDynamicSQL mapper1 = openSession.getMapper(EmployeeMapperDynamicSQL.class);
//
//            Employee employee = new Employee();
//            employee.setLastName("e");
//            List<Employee> empsTestInnerParameter = mapper1.getEmpsTestInnerParameter(employee);
//            for (Employee employee1 : empsTestInnerParameter) {
//                System.out.println(employee1);
//            }
        } finally {
            openSession.close();
        }
    }

    @Test
    public void testFirstLevelCache() throws IOException {
        String resources = "com.atguigu.mybatis.dao/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resources);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);


            //***
//
//            Employee empById1 = mapper.getEmpById(1);
//            System.out.println(empById1);
//            System.out.println(empById==empById1);

            //1.不同的SqlSession
//            SqlSession openSession1 = sqlSessionFactory.openSession();
//            EmployeeMapper mapper1 = openSession1.getMapper(EmployeeMapper.class);
//            Employee empById1 = mapper1.getEmpById(1);
//            System.out.println(empById1);
//            System.out.println(empById1==empById);

            //2.查询条件不同
//            Employee empById1 = mapper.getEmpById(2);
//            System.out.println(empById1);

            //3.清空缓存
//            openSession.clearCache();
//            Employee empById1 = mapper.getEmpById(1);
//            System.out.println(empById1);

            //4.执行增删改操作之后
            mapper.addEmp(new Employee(null,"testCache","cache","1"));
            Employee empById1 = mapper.getEmpById(1);
            System.out.println(empById1);
            System.out.println(empById == empById1);
        } finally {
            openSession.close();
        }
    }
}

