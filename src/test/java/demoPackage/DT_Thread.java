package demoPackage;

public class DT_Thread {

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            //Thread thread = new Thread("wx");
            new Thread(()->{
                try {

                    System.out.println("我是线程："+Thread.currentThread() );
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
