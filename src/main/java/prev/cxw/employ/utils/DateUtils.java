package prev.cxw.employ.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/12/10
 * version:1.0
 */
public class DateUtils {
    public static String timeStamp2Date(int seconds,String format) {
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds*1000)));
    }

    public static int getCurrentTime(){
        return (int)System.currentTimeMillis()/1000;
    }
}
