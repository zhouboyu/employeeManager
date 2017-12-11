package prev.cxw.employ.dao;

import com.google.common.collect.Lists;
//import org.apache.ibatis.annotations.*;
import org.springframework.beans.BeanUtils;
import prev.cxw.employ.dao.impl.LocalCompanyDAO;
import prev.cxw.employ.model.dto.Company;
import prev.cxw.employ.model.dto.Employee;
import prev.cxw.employ.model.query.EmployeeQuery;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */
public interface EmployeeDAO {

//    /**
//     * 查询所有的公司
//     * @return
//     */
//    @SelectProvider(type=prev.cxw.employ.dao.sqlprovider.EmployeeSqlProvider.class,
//            method = "searchEmployee")
//    List<Employee> search(EmployeeQuery query);
//
//    /**
//     * 插入
//     * @param employee
//     */
//    @Insert("insert into Employee(name,idCard,sex,phone,companyId) " +
//            " values(#{name},#{idCard},#{sex},#{phone},#{companyId})")
//    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
//    void insert(Employee employee);
//
//    /**
//     * 更改
//     * @param employee
//     */
//    @Update("update Employee " +
//            "set name=#{name},idCard=#{idCard}," +
//            "phone=#{phone},sex=#{sex},companyId=#{companyId} " +
//            "where id=#{id}")
//    void save(Employee employee);

    List<Employee> employees = Lists.newArrayList(
            new Employee(1,"员工A","411202199009245015",1,"13588361072",1,"大堂经理"),
            new Employee(2,"员工B","411202199009245015",1,"13588361072",1,"大堂经理"),
            new Employee(3,"员工C","411202199009245015",1,"13588361072",1,"大堂经理")
    );

    CompanyDAO getCompanyDao();

    default void insert(Employee employee){
        employee.setId(employees.size());
        employees.add(employee);
    }
    default List<Employee> search(EmployeeQuery query){
        employees.forEach(e-> {
            Company company = getCompanyDao().getById(e.getCompanyId());
            if(company != null){
                e.setCompany(company);
            }else{
                e.setCompany(new Company());
            }
        });
        return employees;
    }
    default void save(Employee employee){
        employees.forEach(e->{
            if(e.getId()!=employee.getId()){
                return;
            }
            try{
                BeanUtils.copyProperties(e,employee);
            }catch (Exception ex){

            }
        });
    }

}
