package demoPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(format.format(new Date()));
//
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));

//        System.out.println(getCurrTimeStr("yyyy-MM-dd HH:mm:ss S"));
//        System.out.println(getCurrTimeStr("yyyyMMddHHmmssS"));
//        System.out.println(getCurrTimeStr("HHmmss"));
//        System.out.println(getCurrTimeStr("yyyy-MM-dd"));


//        String startDate = "2020-01-15";
//        String endDate = "2023-03-01";
//        LocalDate start = LocalDate.parse(startDate);
//        LocalDate end = LocalDate.parse(endDate);
//        //LocalDate end = LocalDate.parse(DT_LocalDate.getDateStr(new Date(),"yyyy-MM-dd"));
//        List<String> months = getMonthsBetween(start, end);
//        months.forEach(System.out::println);

//        try {
//            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-01");
//            Date endDate = new Date();
//            List<String> months = DT_LocalDate.getMonthsBetween2(startDate,endDate);
//            months.forEach(System.out::println);
//        }catch (Exception e){
//            System.out.println("失败："+e.getMessage());
//        }

//        try {
//            Date curDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-03");
//            //Date curDate = new Date();
//            System.out.println(curDate.getDay());
//            System.out.println(curDate.getDate());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        try {
            Calendar curCal = Calendar.getInstance();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date testDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-03");
            curCal.setTime(testDate);

            curCal.set(Calendar.DAY_OF_MONTH, 1);
            curCal.add(Calendar.MONTH,-1);
            //设置开始日期
            System.out.println(sdf.format(curCal.getTime()));
            curCal.add(Calendar.MONTH,1);
            curCal.add(Calendar.DAY_OF_MONTH,-1);
            //设置结束日期
            System.out.println(sdf.format(curCal.getTime()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


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

    public static String getDateStr(Date date,String format){
        switch (format){
            case "yyyy":
            case "MM":
            case "dd":
            case "HH":
            case "mm":
            case "ss":
            case "yyyy-MM-dd":
            case "yyyy/MM/dd":
            case "yyyyMMdd":
            case "yyyyMMddHHmmss":
            case "yyyy-MM-dd HH:mm:ss":
            case "yyyy/MM/dd HH:mm:ss":
            case "yyyy-MM-dd HH:mm:ss S":
            case "yyyyMMddHHmmssS":
            case "HHmmss":
            case "HH:mm:ss":
                try {
                    SimpleDateFormat df = new SimpleDateFormat(format);
                    return df.format(date);
                }catch (Exception e){
                    return "";
                }
            default:
                //如有新的日期格式需求，添加新的case即可
                //return null;
                return "";
        }
    }

//    public static List<String> getMonthsBetween(LocalDate start, LocalDate end) {
//        List<String> months = new ArrayList<>();
//        LocalDate current = start;
//        while (current.isBefore(end) || current.isEqual(end)) {
//            months.add(current.format(DateTimeFormatter.ISO_DATE));
//            if (current.getMonth() == java.time.Month.DECEMBER) {
//                current = current.plusYears(1);
//            }
//            current = current.plusMonths(1);
//        }
//        return months;
//    }


    private static List<String> getMonthsBetween2(Date startDate,Date endDate){
        List<String> periodList = new ArrayList<>();

        if (null == startDate || null == endDate){
            return periodList;
        }
        // 使用Calendar类设置到当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        // 将日历的日期设置为本月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        // 获取date实例对象
        Date currDate = calendar.getTime();

        for (;endDate.after(currDate) || endDate.equals(currDate);){
            periodList.add(new SimpleDateFormat("yyyy-MM").format(currDate));
            calendar.setTime(currDate);
            calendar.add(Calendar.MONTH,1);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            currDate = calendar.getTime();
        }
        return periodList;
    }
}
