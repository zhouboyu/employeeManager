package prev.cxw.employ.web;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import prev.cxw.employ.dao.AdminDAO;
import prev.cxw.employ.model.dto.Admin;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhouby on 17-11-30.
 */
@Controller
public class ManagerController {

    @Resource
    AdminDAO adminDAO;

    @RequestMapping("/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password")String password){


        List<Admin> admins = adminDAO.searchAll();
        if(CollectionUtils.isEmpty(admins)){
            return "用户名或密码错误";
        }
        Admin userAdmin = null;
        for(Admin admin:admins){
            if(admin.getAccount().equals(account)
                    && userAdmin.getPassword().equals(password)){
                userAdmin = admin;
                break;
            }
        }
        if(userAdmin == null){
            return "用户名或密码错误";
        }
        return JSON.toJSONString(userAdmin);
    }

}
