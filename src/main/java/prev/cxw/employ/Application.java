package prev.cxw.employ;

import com.alibaba.fastjson.JSON;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prev.cxw.employ.dao.AdminDAO;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */
@RestController
@EnableAutoConfiguration
@MapperScan("prev.cxw.employ.dao")
public class Application {

    @Resource
    AdminDAO dao;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/now")
    String hehe() {
        return "现在时间：" + (new Date()).toLocaleString();
    }

    @RequestMapping("/admins")
    String admins(){
        return JSON.toJSONString(dao.searchAll());
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
