package demoPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DT_LocalDate {


    public static void main(String[] args) {


//        LocalDate timeNow = LocalDate.now();
//        System.out.println(timeNow);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        LocalDate now = LocalDate.now();
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String strDate = now.toString();
//
//        Date date = new Date();
//        System.out.println(date);
//        String format = simpleDateFormat.format(date);
//        System.out.println(format);


//        LocalDate now = LocalDate.now();
//        String s = now.toString();
//        System.out.println(s);

//        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//
//        try {
//            Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format);
//            System.out.println(parse.toString());
//            System.out.println(new Date().toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        LocalDate now1 = LocalDate.now();
//        System.out.println(now1);
//        LocalTime now2 = LocalTime.now();
//        System.out.println(now2);

//        LocalDateTime now = LocalDateTime.now();
//        String timeStr = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//
//        System.out.println(timeStr);


//        System.out.println(getCurrTimeDate("yyyy"));
//        System.out.println(getCurrTimeDate("MM"));
//        System.out.println(getCurrTimeDate("dd"));
//        System.out.println(getCurrTimeDate("HH"));
//        System.out.println(getCurrTimeDate("mm"));
//        System.out.println(getCurrTimeDate("ss"));
//        System.out.println(getCurrTimeDate("yyyy-MM-dd"));
//        System.out.println(getCurrTimeDate("yyyy/MM/dd"));
//        System.out.println(getCurrTimeDate("yyyyMMddHHmmss"));
//        System.out.println(getCurrTimeDate("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(getCurrTimeDate("yyyy/MM/dd HH:mm:ss"));
//        System.out.println(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(new Date()));


//        System.out.println(getCurrTimeStr("yyyy-MM-dd HH:mm:ss S"));
//        System.out.println(getCurrTimeStr("yyyyMMddHHmmssS"));
//        System.out.println(getCurrTimeStr("HHmmss"));
//        System.out.println(getCurrTimeStr("yyyy-MM-dd"));
    }

    private static Date getCurrTimeDate(String format){
        switch (format){
            case "yyyy":
            case "MM":
            case "dd":
            case "HH":
            case "mm":
            case "ss":
            case "yyyy-MM-dd":
            case "yyyy/MM/dd":
            case "yyyyMMddHHmmss":
            case "yyyy-MM-dd HH:mm:ss":
            case "yyyy/MM/dd HH:mm:ss":
            case "yyyy-MM-dd HH:mm:ss S":
                LocalDateTime now = LocalDateTime.now();
                String timeStr = now.format(DateTimeFormatter.ofPattern(format));
                Date date = null;
                try {
                    date = new SimpleDateFormat(format).parse(timeStr);
                } catch (ParseException e) {
                    //时间转换异常
                    e.printStackTrace();
                }
                return date;
            default:
                //如有新的日期格式需求，添加新的case即可
                return null;
        }
    }

    private static String getCurrTimeStr(String format){
        switch (format){
            case "yyyy":
            case "MM":
            case "dd":
            case "HH":
            case "mm":
            case "ss":
            case "yyyy-MM-dd":
            case "yyyy/MM/dd":
            case "yyyyMMddHHmmss":
            case "yyyy-MM-dd HH:mm:ss":
            case "yyyy/MM/dd HH:mm:ss":
            case "yyyy-MM-dd HH:mm:ss S":
            case "yyyyMMddHHmmssS":
            case "HHmmss":
                LocalDateTime now = LocalDateTime.now();
                return now.format(DateTimeFormatter.ofPattern(format));

            default:
                //如有新的日期格式需求，添加新的case即可
                //return null;
                return "";
        }
    }
}
