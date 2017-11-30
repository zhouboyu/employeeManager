package prev.cxw.employ.model.query;

import lombok.Data;

/**
 * Created by zhouby on 17-11-30.
 */
@Data
public class EmployeeQuery {

    private Long id;
    private String name;
    private String idCard;
    private Integer sex;
    private String phone;
    private Long companyId;


}
