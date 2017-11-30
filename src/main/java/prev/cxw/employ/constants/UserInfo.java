package prev.cxw.employ.constants;

import prev.cxw.employ.model.dto.Admin;

/**
 * Created by zhouby on 17-12-1.
 */
public class UserInfo {

    private static ThreadLocal<Admin> loginUser = new ThreadLocal<Admin>();

    public static void setUser(Admin admin){
        if(admin == null)
            return;
        loginUser.set(admin);
    }

    public static Admin getUser(){
        return loginUser.get();
    }
}
