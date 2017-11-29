package prev.cxw.employ.dao.sqlprovider;

import org.apache.ibatis.jdbc.SQL;
import prev.cxw.employ.model.query.EmployeeQuery;

/**
 * Created by zhouby on 17-11-30.
 */
public class EmployeeSqlProvider {

    public String searchEmployee(final EmployeeQuery query){
        return new SQL(){
            {
                SELECT("*");
                FROM("Employee");
                if(query.getId()!=null){
                    WHERE("id = #{id}");
                }
//                if(query.getId()!=null){
//                    WHERE("id = #{name}");
//                }
//                if(map.containsKey("age")){
//                    WHERE("age=#{age}");
//                }
            }
        }.toString();
    }


}
