package com.fushan.common.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     *  Date 类型转换为 String 类型
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(date == null){
            return null;
        }
        return sdf.format(date);
    }

}
