package prev.cxw.employ.dao;

import org.apache.ibatis.annotations.Select;
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
    @Select("select * from Admin")
    public List<Admin> searchAll();
}
