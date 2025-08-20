package demoPackage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DT_String {


    public static void main(String[] args) {
//        System.out.println("\ntest1");
//        System.out.println(test1());
//        System.out.println("\ntest2");
//        System.out.println(test2());
//        System.out.println("\ntest3");
//        System.out.println(test3());


//        String str = "a b c";
//        String[] s = str.split(" ");
//        for (String s1 : s) {
//            System.out.println(s1);
//        }

//        String str2 = "a b c\nd";
//        System.out.println("old:"+str2);
//        str2 = str2.replace(" ", "");
//        str2 = str2.replace("\n", "");
//        System.out.println("new:"+str2);

//        String str = "Hello\nWorld";
//        String newStr = str.replace("\n", " - ");
//        System.out.println(newStr); // 输出 "Hello - World"

//        String str3 = "";
//        System.out.println(str3.concat("123"));

//        String str = "王旭";
//        System.out.println(str.length());


//        String str = "a-b-c";
//        String[] split = str.split("-");
//        System.out.println(split[0]);
//
//        String str2 = "abc";
//        String[] split2 = str2.split("-");
//        System.out.println(split2[0]);

//        String str = "a-b-c";
//        int i = str.indexOf("*");
//        System.out.println(i);
//        System.out.println(str.substring(str.indexOf("-")+1,str.length()));

//        String str = null;
//        String[] split = str.split("-");

//        String str = "";
//        String[] split = str.split("-");
//        System.out.println(split[1]);



//        try {
//            int a = 1/0;
//        }
//        catch (ArithmeticException e){
//            System.out.println("ArithmeticException "+e);
//        }
//        catch (Exception e){
//            System.out.println("Exception "+e);
//        }finally {
//            System.out.println("测试完成");
//        }

//        //String str = "aaaa";
//        String str2 = "-a-b-c";
//        System.out.println(str.indexOf("-"));
//        //System.out.println(str.indexOf("*"));

        //String str = "1";
        //System.out.println(str);

//        long a = 1;
//        String format = String.format("%010d", a);
//        System.out.println(format);
//        String format2 = String.format("%010d", 222);
//        System.out.println(format2);

//        String str = String.format("%010d", 1);
//        System.out.println(str);
//        Long aLong = Long.valueOf(str);
//        System.out.println(aLong);
//        BigDecimal one = BigDecimal.ONE;
//        String format = new DecimalFormat("0000000000").format(one);
//        System.out.println(format);

//        String str = "1";
//        System.out.println(str.indexOf("-"));
//        String str2 = "-1";
//        System.out.println(str2.indexOf("-"));
//        String str3 = "2024-07-09";
//        System.out.println(str3.indexOf("-"));

//        System.out.println(String.format("%05d", 6));
//        System.out.println(String.format("%05d", 12));
//
//        System.out.println(String.format("%10s", "12").replace(" ","*"));
//        System.out.println(String.format("%-10s", "12").replace(" ","*"));
//        System.out.println(String.format("%-10s", "核心系统").replace(" ","*"));
//        System.out.println(String.format("%-10s", "计量").replace(" ","*"));
//
//        Date date = new Date();
//        int year = date.getYear()+1900;
//        int month = date.getMonth();
//        System.out.println(year+"-"+(String.format("%02d",month)));
//
//
//        System.out.println("123".equals(null));


//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.addAll(null);

/*        System.out.println(String.format("%s123","aaa"));

        System.out.println(String.format("%s-%s","a","b"));

        System.out.println("10".equals(null));*/

        System.out.println(Boolean.FALSE.toString());
    }

    private static String test1(){
        String jsonStr = "{";
        String keyNameTotal = "total";
        jsonStr = jsonStr+"\""+keyNameTotal+"\""+":"+"\""+"1"+"\""+",";
        String key = "historyPassword1";
        jsonStr = jsonStr+"\""+key+"\""+":"+"\""+"password"+"\"";
        jsonStr = jsonStr+"}";
        return jsonStr;
    }

    private static String test2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("123");
        list.add("!@#$");
        String jsonStr = "{";
        for (int index = 0; index<list.size(); index++){
            String key = "historyPassword"+(index+1);
            jsonStr = jsonStr+"\""+key+"\""+":"+"\""+list.get(index)+"\""+(index+1>=list.size()?"":",");
        }
        jsonStr = jsonStr+"}";
        System.out.println(jsonStr);

        System.out.println("替换尾部的}---->"+jsonStr.replace("}",","));
        return jsonStr;
    }


    private static String test3(){
        String source = "{\"total\":\"1\",\"historyPassword1\":\"password\"}";
        String jsonStr = source.replace("}", ",");
        String pwdKeyName = "historyPassword"+(1+1);
        jsonStr = jsonStr+"\""+pwdKeyName+"\""+":"+"\""+"newpassword"+"\""+"}";
        return jsonStr;
    }
}
