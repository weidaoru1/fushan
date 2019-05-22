package com.fushan.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 2019-05-14T03:02 转换 Date 类型
     * @param dateStr
     * @return
     */
    public static Date StrTtoDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        try{
            return sdf.parse(dateStr.replace("T"," "));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
