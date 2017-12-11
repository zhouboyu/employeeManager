package prev.cxw.employ.dao.sqlprovider;

//import org.apache.ibatis.jdbc.SQL;
import prev.cxw.employ.model.query.EmployeeLogQuery;
import prev.cxw.employ.model.query.EmployeeQuery;

/**
 * Created by zhouby on 17-11-30.
 */
public class EmployeeSqlProvider {

//    public String searchEmployee(final EmployeeQuery query){
//        return new SQL(){
//            {
//                SELECT("*");
//                FROM("Employee");
//                WHERE(" 1 = 1");
//                if(query.getId()!=null){
//                    WHERE("id = #{id}");
//                }
//                if(query.getSex()!=null){
//                    WHERE("sex = #{sex}");
//                }
//                if(query.getCompanyId()!=null){
//                    WHERE("companyId = #{companyId}");
//                }
//                if(query.getIdCard()!=null){
//                    WHERE("idCard like '%#{idCard}%'");
//                }
//                if(query.getName()!=null){
//                    WHERE("name like '%#{name}%'");
//                }
//            }
//        }.toString();
//    }
//
//    public String searchEmployeeLog(final EmployeeLogQuery query){
//        return new SQL(){
//            {
//                SELECT("*");
//                FROM("EmployeeLog");
//                WHERE(" 1 = 1");
//                if(query.getCompanyId()!=null){
//                    WHERE("companyId = #{companyId}");
//                }
//                if(query.getEmployeeId()!=null){
//                    WHERE("employeeId = #{employeeId}");
//                }
//                if(query.getEmployeeName()!=null){
//                    WHERE("employeeName like '%#{employeeName}%'");
//                }
//                if(query.getCompanyName()!=null){
//                    WHERE("companyName like '%#{companyName}%'");
//                }
//            }
//        }.toString();
//    }


}
