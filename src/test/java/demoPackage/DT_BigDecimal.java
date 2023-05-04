package demoPackage;

import java.math.BigDecimal;

public class DT_BigDecimal {

    public static void main(String[] args) {
        //被加数
        BigDecimal bigDecimal = new BigDecimal(10);
        System.out.println("被加数: "+bigDecimal+"\n");

        //加数
        BigDecimal addEnd = new BigDecimal(20);
        System.out.println("加数: "+addEnd+"\n");

        //求和结果有问题
        bigDecimal.add(addEnd);
        System.out.println("求和结果有问题: "+bigDecimal+"\n");

        //求和结果没问题
        BigDecimal resultBigDecimal= bigDecimal.add(addEnd);
        System.out.println("求和结果无问题: "+resultBigDecimal+"\n");

        System.out.println(bigDecimal.add(BigDecimal.ONE));


//        String dirPath =  "a\\b";
//        String fileName = "wangxu";
//        String txt = ".txt";
//        System.out.println(dirPath + "\\" + fileName + txt);
//
//        System.out.println(dirPath);
//        String replaceStr = dirPath.toString().replace("\\\\", "\\");
//        System.out.println(replaceStr);
//
//        System.out.println(replaceStr + "\\" + fileName + txt);
    }
}
