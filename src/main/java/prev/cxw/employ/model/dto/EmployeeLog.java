package prev.cxw.employ.model.dto;

import lombok.Data;

/**
 * Created by zhouby on 17-11-30.
 */
@Data
public class EmployeeLog {

    private long id;
    private long employeeId;
    private long companyId;
    private String employeeName;
    private String companyName;
    /**
     * 动作
     * entry 入职
     * leave 离职
     */
    private String action;
    private int created;
    /**
     * 操作人id
     */
    private String adminAccount;
    /**
     * 职务
     */
    private String position;

    private String createTime;

    public EmployeeLog(){}

    public EmployeeLog(long id,long employeeId,long companyId,String name,
                       String cname,String action,int created,String adminAccount,
                       String position
    ){
        this.id = id;
        this.employeeId = employeeId;
        this.companyId = companyId;
        this.employeeName = name;
        this.companyName = cname;
        this.action = action;
        this.created = created;
        this.adminAccount = adminAccount;
        this.position = position;
    }

}
