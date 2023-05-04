package demoPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DT_SimpleDateFormat {

    public static void main(String[] args) throws ParseException {

//        Date date = new Date();
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(date.toString()));

//        System.out.println(Integer.parseInt("20221123"));
//
//
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = df.format(new Date());
        System.out.println(format1);

//        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        System.out.println(format + " " +format.length());
//
//        String format2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        System.out.println(format2);
//
//        System.out.println("2022-11-23"+" "+"20:34:00");
//
//
//        System.out.println("57386-06-25".length());
//
//        System.out.println(new Date());

//        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        System.out.println(dateStr);
//
//        Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
//        System.out.println(startDate);
//        System.out.println(startDate.toString());
//
//
//        System.out.println(new Date());

        //Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse("1997-01-01");
        //Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-01");
//        Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-01");
//        System.out.println("间隔"+differentDays(startDate, new Date())+"天");


//        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-27 08:30:10");
//        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-27 08:30:11");
//        System.out.println(date1.compareTo(date2));
    }


    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    private static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) {//同一年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        } else {// 不同年
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
