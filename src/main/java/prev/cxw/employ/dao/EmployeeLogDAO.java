package prev.cxw.employ.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import prev.cxw.employ.model.dto.EmployeeLog;
import prev.cxw.employ.model.query.EmployeeLogQuery;

import java.util.List;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/30
 * version:1.0
 */
public interface EmployeeLogDAO {
    /**
     * 查询所有的公司
     * @return
     */
    @SelectProvider(type=prev.cxw.employ.dao.sqlprovider.EmployeeSqlProvider.class,
            method = "searchEmployeeLog")
    List<EmployeeLog> search(EmployeeLogQuery query);

    /**
     * 插入
     * @param employee
     */
    @Insert("insert into EmployeeLog" +
            "(employeeId,companyId,employeeName,companyName,entryTime,leaveTime) " +
            " values" +
            "(#{employeeId},#{companyId},#{employeeName},#{companyName},#{entryTime}),#{leaveTime}")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
    void insert(EmployeeLog employee);

    /**
     * 更改
     * @param employee
     */
    @Update("update EmployeeLog " +
            "set employeeId=#{employeeId},companyId=#{companyId}," +
            "employeeName=#{employeeName},companyName=#{companyName}," +
            "entryTime=#{entryTime},leaveTime=#{leaveTime}" +
            "where id=#{id}")
    void save(EmployeeLog employee);

}
