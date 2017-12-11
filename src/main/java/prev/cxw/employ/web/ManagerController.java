package prev.cxw.employ.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import prev.cxw.employ.constants.UserInfo;
import prev.cxw.employ.dao.AdminDAO;
import prev.cxw.employ.dao.CompanyDAO;
import prev.cxw.employ.dao.EmployeeDAO;
import prev.cxw.employ.dao.EmployeeLogDAO;
import prev.cxw.employ.model.dto.Admin;
import prev.cxw.employ.model.dto.Company;
import prev.cxw.employ.model.dto.Employee;
import prev.cxw.employ.model.dto.EmployeeLog;
import prev.cxw.employ.model.query.CompanyQuery;
import prev.cxw.employ.model.query.EmployeeLogQuery;
import prev.cxw.employ.model.query.EmployeeQuery;
import prev.cxw.employ.utils.DateUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.google.common.collect.ImmutableMap.of;
import static prev.cxw.employ.constants.SystemConstants.SESSION_USERKEY;

/**
 * Created by zhouby on 17-11-30.
 */
@Controller
public class ManagerController {

    @Resource
    AdminDAO adminDAO;
    @Resource
    CompanyDAO companyDAO;
    @Resource
    EmployeeDAO employeeDAO;
    @Resource
    EmployeeLogDAO logDAO;


    @RequestMapping(path = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam("account") String account,
                        @RequestParam("password")String password,
                        HttpServletRequest request){
        if(StringUtils.isEmpty(account)
                || StringUtils.isEmpty(account)){
            return of(
                    "success",false,
                    "msg","用户名或密码错误"
            );
        }

        List<Admin> admins = adminDAO.searchAll();
        if(CollectionUtils.isEmpty(admins)){
            return of(
                    "success",false,
                    "msg","用户名或密码错误"
            );
        }
        Admin userAdmin = null;
        for(Admin admin:admins){
            if(admin.getAccount().equals(account)
                    && admin.getPassword().equals(password)){
                userAdmin = admin;
                break;
            }
        }
        if(userAdmin == null){
            return of("success",false,
                    "msg","用户名或密码错误");
        }
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(SESSION_USERKEY,userAdmin);
        return of("success",true);
    }

    @RequestMapping("/toLogin")
    public String test(){
        return "login";
    }

    @RequestMapping("/tohome")
    public String tohome(){
        return "home";
    }

    @RequestMapping("/toUserList")
    public String toUserLIst(){
        return "userList";
    }

    @RequestMapping("/userListInfo")
    @ResponseBody
    public Object userListInfo(){
        return of("success",true,"coms",companyDAO.searchAll());
    }

    @RequestMapping("/addemployee")
    @ResponseBody
    public Object addemployee(HttpServletRequest request){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int sex = NumberUtils.toInt(request.getParameter("sex"));
        String idCard = request.getParameter("idCard");
        long companyId = NumberUtils.toLong(request.getParameter("comselect"));
        EmployeeQuery query = new EmployeeQuery();
        query.setIdCard(idCard);
        List<Employee> employees = employeeDAO.search(query);
        //调试用
        employees = new ArrayList<>();
        if(employees != null && employees.size()!=0){
            return of("success",false,"msg","该身份证号的员工已存在");
        }
        Employee employee = new Employee();
        employee.setIdCard(idCard);
        employee.setName(name);
        employee.setPhone(phone);
        employee.setSex(sex);
        employee.setCompanyId(companyId);
        employeeDAO.insert(employee);
        return of("success",true);
    }

    @RequestMapping("/queryEmployee")
    @ResponseBody
    public Object queryEmployee(HttpServletRequest request){
        EmployeeQuery query = new EmployeeQuery();
        String name = request.getParameter("name");
        if(name != null && "".equals(name)){
            query.setName(name);
        }
        String phone = request.getParameter("phone");
        if(phone != null && "".equals(phone)){
            query.setPhone(phone);
        }
        String idCard = request.getParameter("idCard");
        if(idCard != null && "".equals(idCard)){
            query.setIdCard(idCard);
        }
        String sexString = request.getParameter("sex");
        if(sexString != null && "".equals(sexString)){
            query.setSex(NumberUtils.toInt(sexString));
        }
        String comselect = request.getParameter("comselect");
        if(comselect != null && "".equals(comselect)){
            query.setCompanyId(NumberUtils.toLong(comselect));
        }
        List<Employee> employees = employeeDAO.search(query);
        return of("success",true,"list",employees);
    }

    @RequestMapping("/addcompany")
    @ResponseBody
    public Object addCompany(HttpServletRequest request){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String detail = request.getParameter("detail");
        Company company = new Company();
        company.setPhone(phone);
        company.setName(name);
        company.setAddress(address);
        company.setDetail(detail);
        companyDAO.insert(company);
        return of("success",true);
    }



    @RequestMapping("/queryCompany")
    @ResponseBody
    public Object queryCompany(HttpServletRequest request){
        CompanyQuery query = new CompanyQuery();
        String name = request.getParameter("name");
        if(name != null && "".equals(name)){
            query.setName(name);
        }
        String phone = request.getParameter("phone");
        if(phone != null && "".equals(phone)){
            query.setPhone(phone);
        }
        String address = request.getParameter("address");
        if(address != null && "".equals(address)){
            query.setAddress(address);
        }
        List<Company> companys = companyDAO.search(query);
        return of("success",true,"list",companys);
    }


    @RequestMapping("/toUserDetail")
    public ModelAndView toUserDetail(HttpServletRequest request){

        String eid = request.getParameter("eid");
        if(StringUtils.isEmpty(eid)){
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errormsg", "无效的参数");
            return mav;
        }
        EmployeeQuery query = new EmployeeQuery();
        query.setId(NumberUtils.toLong(eid,0));
        List<Employee> employees = employeeDAO.search(query);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(employees)){
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errormsg", "没有这个员工");
            return mav;
        }
        ModelAndView mav = new ModelAndView("employeeDetail");
        mav.addObject("employee", employees.get(0));
        EmployeeLogQuery logquery = new EmployeeLogQuery();
        logquery.setEmployeeId(query.getId());
        List<EmployeeLog> logs = logDAO.search(logquery);
        logs.forEach(log-> log.setCreateTime(DateUtils.timeStamp2Date(log.getCreated(),"YYYY-dd-mm")));
        mav.addObject("loglist",logs);
        return mav;
    }

    @RequestMapping("/checkEmployeeStatus")
    @ResponseBody
    public Object checkEmployeeStatus(HttpServletRequest request){
        String eid = request.getParameter("eid");
        if(StringUtils.isEmpty(eid)){
            return of("success",false,"msg","param is valid");
        }
        EmployeeQuery query = new EmployeeQuery();
        query.setId(NumberUtils.toLong(eid,0));
        List<Employee> employees = employeeDAO.search(query);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(employees)){
            return of("success",false,"msg","没有这个员工");
        }
        Employee employee = employees.get(0);
        return of("success",true,"work",employee.getCompanyId() != 0);
    }

    @RequestMapping("/doLeave")
    @ResponseBody
    public Object doLeave(HttpServletRequest request){
        String eid = request.getParameter("eid");
        if(StringUtils.isEmpty(eid)){
            return of("success",false,"msg","param is valid");
        }
        EmployeeQuery query = new EmployeeQuery();
        query.setId(NumberUtils.toLong(eid,0));
        List<Employee> employees = employeeDAO.search(query);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(employees)){
            return of("success",false,"msg","没有这个员工");
        }
        Employee employee = employees.get(0);
        long oldCompanyId = employee.getCompanyId();
        CompanyQuery cquery = new CompanyQuery();
        cquery.setId(oldCompanyId);

        List<Company> companies = companyDAO.search(cquery);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(companies)){
            return of("success",false,"msg","员工所属公司无效");
        }
        Company company = companies.get(0);
        String oldCompanyName = company.getName();
        employee.setCompanyId(0);
        employee.setPosition("");
        employeeDAO.save(employee);

        EmployeeLog log = new EmployeeLog();
        log.setCompanyId(oldCompanyId);
        log.setCompanyName(oldCompanyName);
        log.setEmployeeId(employee.getId());
        log.setEmployeeName(employee.getName());
        log.setAction("leave");
        log.setAdminAccount(UserInfo.getUser().getAccount());
        log.setCreated(DateUtils.getCurrentTime());
        logDAO.add(log);
        return of("success",true);
    }


    @RequestMapping("/doEntry")
    @ResponseBody
    public Object doEntry(HttpServletRequest request){
        String eid = request.getParameter("eid");
        String cid = request.getParameter("cid");
        String position = request.getParameter("position");
        if(StringUtils.isEmpty(eid) || StringUtils.isEmpty(cid)
                || StringUtils.isEmpty(position)){
            return of("success",false,"msg","param is valid");
        }
        EmployeeQuery query = new EmployeeQuery();
        query.setId(NumberUtils.toLong(eid,0));
        List<Employee> employees = employeeDAO.search(query);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(employees)){
            return of("success",false,"msg","没有这个员工");
        }

        CompanyQuery query2 = new CompanyQuery();
        query2.setId(NumberUtils.toLong(cid,0));
        List<Company> coms = companyDAO.search(query2);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(coms)){
            return of("success",false,"msg","无效公司");
        }

        Employee employee = employees.get(0);
        employee.setPosition(position);
        employee.setCompanyId(NumberUtils.toLong(cid));
        employeeDAO.save(employee);

        Company com = coms.get(0);

        EmployeeLog log = new EmployeeLog();
        log.setCompanyId(NumberUtils.toLong(cid));
        log.setCompanyName(com.getName());
        log.setEmployeeId(employee.getId());
        log.setEmployeeName(employee.getName());
        log.setAction("entry");
        log.setPosition(position);
        log.setAdminAccount(UserInfo.getUser().getAccount());
        log.setCreated(DateUtils.getCurrentTime());
        logDAO.add(log);
        return of("success",true);
    }


    @RequestMapping("/toCompanyDetail")
    public ModelAndView toCompanyDetail(HttpServletRequest request){
        String cid = request.getParameter("cid");
        if(StringUtils.isEmpty(cid)){
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errormsg", "无效的参数");
            return mav;
        }
        CompanyQuery query = new CompanyQuery();
        query.setId(NumberUtils.toLong(cid,0));
        List<Company> companies = companyDAO.search(query);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(companies)){
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errormsg", "没有这个员工");
            return mav;
        }
        ModelAndView mav = new ModelAndView("companyDetail");
        mav.addObject("company", companies.get(0));
        EmployeeQuery query2 = new EmployeeQuery();
        query2.setCompanyId(NumberUtils.toLong(cid,0));
        List<Employee> employees = employeeDAO.search(query2);
        mav.addObject("elists",employees);
        return mav;
    }

    @RequestMapping("/saveCompany")
    public ModelAndView saveCompany(HttpServletRequest request){
        String cid = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        if(StringUtils.isEmpty(cid) || StringUtils.isEmpty(name) ||
                StringUtils.isEmpty(address) ||StringUtils.isEmpty(phone)){
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errormsg", "无效的参数");
            return mav;
        }
        Company company = companyDAO.getById(NumberUtils.toLong(cid));
        if(company == null){
            ModelAndView mav = new ModelAndView("error");
            mav.addObject("errormsg", "无效的公司");
            return mav;
        }
        company.setName(name);
        company.setAddress(address);
        company.setPhone(phone);
        company.setDetail(detail);
        companyDAO.save(company);
        ModelAndView mav = new ModelAndView("redirect:/toCompanyDetail?cid="+cid);
        mav.addObject("errormsg", "无效的公司");
        return mav;
    }

}
