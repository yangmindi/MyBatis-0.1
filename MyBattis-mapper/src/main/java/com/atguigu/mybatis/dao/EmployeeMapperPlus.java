package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    public Employee getEmpAndDept(Integer id);

    public Employee getEmpById(Integer id);

    public Employee getEmpByIdStep(Integer id);

}
