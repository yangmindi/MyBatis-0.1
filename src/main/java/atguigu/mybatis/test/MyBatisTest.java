package atguigu.mybatis.test;


import atguigu.mybatis.bean.Employee;
import atguigu.mybatis.dao.EmployeeMapper;
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
        String resource = "mybatis-config.xml";
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
        String resource = "mybatis-config.xml";
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
        String resourc = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resourc);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);

    }
}
