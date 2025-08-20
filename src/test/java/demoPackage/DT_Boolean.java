package demoPackage;

public class DT_Boolean {

    public static void main(String[] args) {
//        boolean flag = true;
//        System.out.println(flag);
//        flag = flag&&false;
//        System.out.println(flag);


        try {
            int a= 1/0;
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
}
