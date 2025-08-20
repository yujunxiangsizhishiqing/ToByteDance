package demoPackage;

import java.math.BigDecimal;

public class DT_BigDecimal {
    public static void main(String[] args) {
        //test01();

//        BigDecimal moeny1 = new BigDecimal("10");
//        BigDecimal moeny2 = new BigDecimal("0");
//        BigDecimal moeny3 = new BigDecimal("-10");
//        System.out.println(moeny1.compareTo(BigDecimal.ZERO));
//        System.out.println(moeny2.compareTo(BigDecimal.ZERO));
//        System.out.println(moeny3.compareTo(BigDecimal.ZERO));

        DT_BigDecimal demo = new DT_BigDecimal();
//        demo.test02(BigDecimal.ONE);
//        demo.test02(new BigDecimal("10.11"));
//        demo.test02("123");


        //demo.test03();

        BigDecimal deci = BigDecimal.ONE;
        System.out.println(deci);
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

    private<T> void test02(T value){
        Class<?> aClass = value.getClass();
        if (BigDecimal.class.equals(aClass)){
            System.out.println("BigDecimal 类型数据："+value);
        }else if (String.class.equals(aClass)){
            System.out.println("String 类型数据："+value);
        }
    }

    private void test03(){
        BigDecimal decimal = BigDecimal.ONE;
        System.out.println(decimal.compareTo(null));;
    }
}
