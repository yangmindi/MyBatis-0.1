package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {
    public Employee getEmpAndDept(Integer id);

    public Employee getEmpById(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer deptId);

}
