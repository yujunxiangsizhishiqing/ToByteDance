package demoPackage;

public class DT_StringMethod {

    public static void main(String[] args) {
        //String spStr = "1,2,3,,5,6";
        //String[] splitArr = spStr.split(",");

//        String spStr = "1||2||3||||5||6";
//        String[] splitArr = spStr.split("\\|\\|");
//        System.out.println(splitArr.length);
//        for (String s : splitArr) {
//            if (s==null){
//                System.out.println("当前字符串: null ");
//            }else{
////                if (s.equals("")){
////                    System.out.println("当前字符串为空字符");
////                }
//                if (s.length()<1){
//                    System.out.println("当前字符串: 空字符");
//                    continue;
//                }
//                System.out.println("当前字符串： " + s);
//            }
//        }


//        Spliterator<String> spliterator = Stream.of(spStr).spliterator();


//        String format = String.format("%08d", 1);
//        System.out.println(format);

        for (int i=0;i<10;i++){
            System.out.println("testA"+String.format("%08d",i));
        }
    }
}
