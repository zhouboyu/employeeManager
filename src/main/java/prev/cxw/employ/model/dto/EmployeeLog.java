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
    private int entryTime;
    private int leaveTime;

}
