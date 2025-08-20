package demoPackage;

public class DT_CompareStringBufferAndString {

    public static void main(String[] args) {

        System.out.println("------------------StringBuffer测试-----------------------");
        StringBuffer errMsg = new StringBuffer();
        System.out.println("处理之前errMsg为:"+errMsg.toString());
        paramChange2(errMsg);
        System.out.println("处理之后errMsg为:"+errMsg.toString());

        System.out.println("\n");

        System.out.println("------------------String测试----------------------");
        String str = "123";
        System.out.println("处理之前str的为:"+str);
        paramChange(str);
        System.out.println("处理之后str的为:"+str);
    }

    private static void paramChange(String param){
        param = param+"abc";
        System.out.println("paramChange中的参数数值为："+param);
    }

    private static void paramChange2(StringBuffer errMsg){
        errMsg.append("aaa");
        System.out.println("处理中途errMsg为:"+errMsg.toString());
    }

}
