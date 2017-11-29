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
public class Company {
    private long id;
    private String name;
    private String address;
    private String phone;
    private String detail;
    private int isDeleted;
}
