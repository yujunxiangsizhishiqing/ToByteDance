package demoPackage;

public class GetCpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cpuCount = runtime.availableProcessors();
        System.out.println("系统当前的CPU数: " + cpuCount);
    }
}
