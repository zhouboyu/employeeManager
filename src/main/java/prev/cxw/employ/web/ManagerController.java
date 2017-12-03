package prev.cxw.employ.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import prev.cxw.employ.dao.AdminDAO;
import prev.cxw.employ.dao.CompanyDAO;
import prev.cxw.employ.model.dto.Admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
}
