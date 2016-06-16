package love.drose.gms.utils;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lovedrose on 2/29/16.
 */
public class DateUtil {

    /**
     * 精确到秒
     */
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到日
     */
    public static final String DATE = "yyyy-MM-dd";

    /**
     * 转换日期字符串为日期对象
     * @param format
     * @param source
     * @return
     */
    public static Date convertStringToDate(String format, String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换日期对象为字符串
     * @param format
     * @param date
     * @return
     */
    public static String convertDateToString(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
