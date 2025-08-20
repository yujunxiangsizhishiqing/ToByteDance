package demoPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DT_Calendar {

    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
//        cal.add(Calendar.MINUTE,20);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
//        cal.add(Calendar.DAY_OF_MONTH,400);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
//        cal.add(Calendar.DAY_OF_YEAR,400);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));

//        Calendar cal1 = Calendar.getInstance();
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal1.getTime())); //结果是 2000-9-30
//        cal1.set(2000, 7, 31, 0, 0 , 0); //2000-8-31
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal1.getTime())); //结果是 2000-9-30
//        cal1.add(Calendar.MONTH, 1); //2000-9-31 => 2000-10-1，对吗？
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal1.getTime())); //结果是 2000-9-30

//        Calendar cal2 = Calendar.getInstance();
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal2.getTime()));
//        cal2.set(Calendar.DAY_OF_MONTH,31);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal2.getTime()));
//        cal2.add(Calendar.DAY_OF_MONTH,1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal2.getTime()));
//        cal2.add(Calendar.DAY_OF_MONTH,-1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal2.getTime()));

//        Date date = new Date();
//        System.out.println((1900+date.getYear())+"-"+ date.getMonth());
//        System.out.println(date.getYear()+"-"+ date.getDay());
//        System.out.println(date.getYear()+"-"+ date.getDate());

        //取上月份的第一天和最后一天
//        Calendar calendar = Calendar.getInstance();
//        //上个月第一天
//        calendar.add(Calendar.MONTH,-1);
//        calendar.set(Calendar.DAY_OF_MONTH,1);
//        System.out.println(calendar.getTime());
//        //上个月最后一弹
//        calendar.add(Calendar.MONTH, 1);
//        calendar.add(Calendar.DATE, -1);
//        System.out.println(calendar.getTime());
//        //会计期间
//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH)+1);


//        // 获取当前日期的Date对象
//        Date currentDate = new Date();
//
//        // 使用Calendar类设置到当前日期
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currentDate);
//
//        // 将日历的日期设置为本月最后一天
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//        // 获取并打印本月最后一天的日期
//        Date lastDayOfMonth = calendar.getTime();
//        System.out.println("本月最后一天的日期是: " + lastDayOfMonth);

        Date currentDate = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(currentDate);
        cal1.add(Calendar.MONTH,-12);
        Date startDate = cal1.getTime();
        Date endDate = currentDate;

        // 使用Calendar类设置到当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        // 将日历的日期设置为本月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        // 获取并打印本月最后一天的日期
        Date currDate = calendar.getTime();

        System.out.println("开始日期是: " + startDate);
        System.out.println("结束日期是: " + endDate);
        //System.out.println(endDate.after(currDate));
        for (;endDate.after(currDate);){
            //retList.add(currDate);
            System.out.println("日期是: " + currDate.getYear()+currDate.getMonth()+currDate.getDate());
            Calendar cal = Calendar.getInstance();
            cal.setTime(currDate);
            cal.add(Calendar.MONTH,1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            currDate = cal.getTime();
        }

    }
}
