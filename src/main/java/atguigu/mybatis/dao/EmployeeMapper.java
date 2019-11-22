package atguigu.mybatis.dao;

import atguigu.mybatis.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);
}
