package myframe.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuyufeng on 2017/5/18.
 */
public class DateConvert {
    private final static SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date valueOf(String str) {
        try {
            return f1.parse(str);
        } catch (Exception e1) {
            try {
                return f2.parse(str);
            } catch (Exception e2) {
                try {
                    return f3.parse(str);
                } catch (Exception e3) {
                    throw new RuntimeException("日期类型转换错误", e3);
                }

            }
        }
    }
}
