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

    public Company(){

    }

    public Company(long id,String name,String address,String phone,String detail){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.detail = detail;
    }

}
