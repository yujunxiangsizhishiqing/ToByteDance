package demoPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DT_Calendar {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        cal.add(Calendar.MINUTE,20);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        cal.add(Calendar.DAY_OF_MONTH,400);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        cal.add(Calendar.DAY_OF_YEAR,400);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
//        Calendar cal1 = Calendar.getInstance();
//        cal1.set(2000, 7, 31, 0, 0 , 0); //2000-8-31
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal1.getTime())); //结果是 2000-9-30
//        cal1.add(Calendar.MONTH, 1); //2000-9-31 => 2000-10-1，对吗？
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal1.getTime())); //结果是 2000-9-30

    }
}
