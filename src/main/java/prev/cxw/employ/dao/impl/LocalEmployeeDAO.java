package prev.cxw.employ.dao.impl;

import org.springframework.stereotype.Component;
import prev.cxw.employ.dao.CompanyDAO;
import prev.cxw.employ.dao.EmployeeDAO;
import prev.cxw.employ.model.dto.Company;

import javax.annotation.Resource;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/12/3
 * version:1.0
 */
@Component
public class LocalEmployeeDAO implements EmployeeDAO {
    @Resource
    CompanyDAO companyDAO;

    @Override
    public CompanyDAO getCompanyDao() {
        return companyDAO;
    }
}
