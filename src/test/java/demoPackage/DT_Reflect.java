package demoPackage;

import demoClass.SMCfgTableErr;

import java.lang.reflect.Method;

public class DT_Reflect {

    private SMCfgTableErr smCfgTableErr;

    public static void main(String[] args) {

        System.out.println();

        try {
            Class<?> c1 = Class.forName("com.other.TestClass.SMCfgTableErr");
            System.out.println(c1.getName());

            String methodName = "SETID";
            if ("setId".equalsIgnoreCase(methodName)){
                Method method = c1.getMethod("setId", String.class);
                //Object o = c1.newInstance();
                Object o = c1.getDeclaredConstructor().newInstance();
                method.invoke(o,"123");
                System.out.println(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
