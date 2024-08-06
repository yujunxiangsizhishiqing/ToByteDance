package demoPackage;

//import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DT_Date {
    public static void main(String[] args) {

//        Date date = new Date(System.currentTimeMillis());
//        System.out.println(date);

//        Date date = new Date();
//        System.out.println(1900+date.getYear());
//        System.out.println(date.getMonth());
//        Date date = new Date(System.currentTimeMillis());
//        System.out.println(date.toString());

//        Date date = new Date();
//        int year = date.getYear()+1900;
//        int month = date.getMonth()+1;
//        System.out.println(year+"-"+(String.format("%02d",month)));


        try {
            //Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-01-01");
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01 01:01:01");
            System.out.println(parse.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
