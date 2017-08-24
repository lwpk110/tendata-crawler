package cn.xinbee.rcs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

public abstract class TradeUtils {

    public static String getTradeNo(){
        Date date=new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = df.format(date).toString();
        return dateString + RandomUtils.nextInt(1000, 9999);
    }
    
    private TradeUtils(){
        
    }
}
