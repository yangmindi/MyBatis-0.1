package atguigu.mybatis.dao;

import atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {
    @Select("select * from tbl_employee where id=#{id}")
    public Employee getEmpById(Integer id);
}
