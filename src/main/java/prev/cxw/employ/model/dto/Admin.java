package prev.cxw.employ.model.dto;

import lombok.Data;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/28
 * version:1.0
 */
@Data
public class Admin {
    private long id;
    private String account;
    private String password;

    public Admin(){

    }

    public Admin(long id,String account,String password){
        this.id = id;
        this.account = account;
        this.password = password;
    }
}
