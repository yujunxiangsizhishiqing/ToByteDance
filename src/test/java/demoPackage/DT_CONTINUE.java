package demoPackage;

public class DT_CONTINUE {

    public static void main(String[] args) {

        for (int i=0;i<5;i++){
            if (i==2){
                continue;
            }else {
                System.out.println(i);
            }
        }
    }
}
