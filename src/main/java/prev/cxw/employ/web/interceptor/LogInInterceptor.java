package prev.cxw.employ.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import prev.cxw.employ.constants.UserInfo;
import prev.cxw.employ.model.dto.Admin;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static prev.cxw.employ.constants.SystemConstants.SESSION_USERKEY;

/**
 * Created by zhouby on 17-12-1.
 * 判断用户是否登录，如果没登录跳转至登录界面
 */
//@Component
public class LogInInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userObj;
        if(null != (userObj = session.getAttribute(SESSION_USERKEY))
                && userObj instanceof Admin){
            UserInfo.setUser((Admin)userObj);
            return super.preHandle(request, response, handler);
        }
        // 跳转登录
        String url = "toLogin";
        response.sendRedirect(url);
        return false;
    }
}
