package demoPackage;

public class DT_Stringbuffer {


    public static void main(String[] args) {

        StringBuffer stringbuffer = new StringBuffer("123");
        StringBuffer aa = stringbuffer.append("aa").append("\n");

        System.out.println(aa.toString());
        System.out.println(stringbuffer);

    }
}
