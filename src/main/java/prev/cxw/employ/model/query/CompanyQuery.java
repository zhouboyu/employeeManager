package prev.cxw.employ.model.query;

import lombok.Data;

/**
 * Created by zhouby on 17-11-30.
 */
@Data
public class CompanyQuery {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String detail;
    private int isDeleted;
}
