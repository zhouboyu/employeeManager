package prev.cxw.employ.model.query;

import lombok.Data;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/30
 * version:1.0
 */
@Data
public class EmployeeLogQuery {

    private Long employeeId;
    private Long companyId;
    private String employeeName;
    private String companyName;

}
