package prev.cxw.employ.dao;

import com.google.common.collect.Lists;
//import org.apache.ibatis.annotations.Select;
import prev.cxw.employ.model.dto.Admin;

import java.util.List;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */

public interface AdminDAO {
//    @Select("select * from Admin")
//    List<Admin> searchAll();

    List<Admin> admins = Lists.newArrayList(new Admin(1,"admin","admin"));

    default List<Admin> searchAll(){
        return admins;
    }
}
