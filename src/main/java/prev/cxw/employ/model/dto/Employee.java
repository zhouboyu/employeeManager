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
    private String position;
    private int isDeleted;
    private long companyId;
    private Company company;

    public Employee(){}

    public Employee(long id,String name,String idCard,int sex,String phone,long companyId,String position){
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.sex = sex;
        this.phone = phone;
        this.companyId = companyId;
        this.position = position;
    }


}
