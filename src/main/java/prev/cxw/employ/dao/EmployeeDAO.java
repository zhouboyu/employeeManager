package prev.cxw.employ.dao;

import org.apache.ibatis.annotations.*;
import prev.cxw.employ.model.dto.Employee;
import prev.cxw.employ.model.query.EmployeeQuery;

import java.util.List;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */
public interface EmployeeDAO {

    /**
     * 查询所有的公司
     * @return
     */
    @SelectProvider(type=prev.cxw.employ.dao.sqlprovider.EmployeeSqlProvider.class,
            method = "searchEmployee")
    List<Employee> search(EmployeeQuery query);

    /**
     * 插入
     * @param employee
     */
    @Insert("insert into Employee(name,idCard,sex,phone,companyId) " +
            " values(#{name},#{idCard},#{sex},#{phone},#{companyId})")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
    void insert(Employee employee);

    /**
     * 更改
     * @param employee
     */
    @Update("update Employee " +
            "set name=#{name},idCard=#{idCard}," +
            "phone=#{phone},sex=#{sex},companyId=#{companyId} " +
            "where id=#{id}")
    void save(Employee employee);

}
