package prev.cxw.employ.model.dto;

import lombok.Data;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */
@Data
public class Employee {

    private long id;
    private String name;
    private String idCard;
    private int sex;
    private String phone;
    private int isDeleted;
    private long companyId;


}
