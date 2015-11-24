package love.drose.gms.converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期转换器
 * Created by lovdrose on 2015/11/23.
 */
public class MyDateConverter implements Converter<String, Date> {

    Logger logger = LogManager.getLogger(MyDateConverter.class);

    private static final List<String> formarts = new ArrayList<String>(4);

    static{
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {
        logger.debug("<== [source:" + source + "]");

        String value = source.trim();
        if ("".equals(value)) {
            logger.debug("==> null");
            return null;
        }
        if(source.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(source, formarts.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formarts.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(3));
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr
     *            String 字符型日期
     * @param format
     *            String 格式
     * @return Date 日期
     */
    public  Date parseDate(String dateStr, String format) {
        logger.debug("<== [dateStr:" + dateStr + ", format:" + format + "]");

        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date =  simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
        }
        logger.debug("==> [date:" + date + "]");
        return date;
    }


    public static void main(String[] args) {
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
             date = myFmt.parse("2014-11-11");
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.err.println(new MyDateConverter().convert("2014-04"));
        System.err.println(date);
    }
}
