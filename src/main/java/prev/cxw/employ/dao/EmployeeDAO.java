package prev.cxw.employ.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import prev.cxw.employ.model.dto.Company;
import prev.cxw.employ.model.dto.Employee;
import prev.cxw.employ.model.query.CompanyQuery;

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
    @Select("select * from Employee " +
            " where name like '%#{name}%'" +
            " and sex = '#{sex}'" +
            " and idCard like '%#{idCard}%'" +
            " and address like '%#{address}%'" +
            "")
    List<Employee> search(CompanyQuery query);

    /**
     * 插入
     * @param company
     */
    @Insert("insert into Company(name,address,phone,detail) " +
            " values(#{name},#{address},#{phone},#{detail})")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
    void insert(Company company);

    /**
     * 更改
     * @param company
     */
    @Update("update Company " +
            "set name=#{name},address=#{address},phone=#{phone},detail=#{detail} " +
            "where id=#{id}")
    void save(Company company);

}
