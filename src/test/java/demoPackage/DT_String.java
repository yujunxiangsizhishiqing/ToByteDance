package demoPackage;

import java.util.ArrayList;

public class DT_String {


    public static void main(String[] args) {
//        System.out.println("\ntest1");
//        System.out.println(test1());
//        System.out.println("\ntest2");
//        System.out.println(test2());
//        System.out.println("\ntest3");
//        System.out.println(test3());


        String str = "a b c";
        String[] s = str.split(" ");
        for (String s1 : s) {
            System.out.println(s1);
        }
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
