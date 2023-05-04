package demoPackage;

import java.util.Calendar;
import java.util.Date;

public class DT_DateUtil {

    public static void main(String[] args) {

        Date date = new Date();
        date.setYear(2022);
        String time = new String("11:22");
        getFullDate(date,time);

//        Date date2 = new Date();
//        date2.setYear(2022);
//        String time2 = new String("11:22:33");
//        getFullDate(date2,time2);
    }

    public static Date getFullDate(Date date, String time) {
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.setTime(date);
        if (time != null && time.indexOf(":") != -1) {
            String[] times = time.split(":");
            tCalendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
            tCalendar.set(Calendar.MINUTE, Integer.valueOf(times[1]));
            tCalendar.set(Calendar.SECOND, Integer.valueOf(times[2]));
        }

        return tCalendar.getTime();
    }
}
