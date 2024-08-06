package demoPackage;

import java.math.BigDecimal;

public class DT_BigDecimal {
    public static void main(String[] args) {
        //test01();

        BigDecimal moeny1 = new BigDecimal("10");
        BigDecimal moeny2 = new BigDecimal("0");
        BigDecimal moeny3 = new BigDecimal("-10");
        System.out.println(moeny1.compareTo(BigDecimal.ZERO));
        System.out.println(moeny2.compareTo(BigDecimal.ZERO));
        System.out.println(moeny3.compareTo(BigDecimal.ZERO));
    }

    private static void test01(){
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
    }
}
