package demoPackage;

public class DT_TryCatch {

    public static void main(String[] args) {
        DT_TryCatch demo = new DT_TryCatch();
        demo.test03();
    }

    private void test01(){

        String a = "a";
        try {
            System.out.println("(1)"+a);
        }catch (Exception e){
            System.out.println("(2)"+a);
            a = "b";
            System.out.println("(3)"+a);
        }finally {
            a = "c";
        }
        System.out.println("(4)"+a);
    }

    private void test02(){

        try {
            System.out.println("try");
            int a = 1/0;
        }catch (Exception e){
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }
    }

    private String test03(){
        String str = "test03";
        try {
            str = "try";
            System.out.println(str);
            int a = 1/0;
        }catch (Exception e){
            str = "err1";
            System.out.println(str);
            return str;
        }finally {
            str = "finally";
            System.out.println(str);
        }
        return str;
    }
}
