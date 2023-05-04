package demoClass;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("cur time: " + System.currentTimeMillis());
//        long timeMillis = System.currentTimeMillis();
//        System.out.println("start time: " + timeMillis);
//        boolean flag = true;
//        while(flag){
//            long timeMillis2 = System.currentTimeMillis();
//            long wait = timeMillis2- timeMillis;
//            if (wait>5){
//                flag = false;
//                System.out.println("end time: " + timeMillis2);
//            }
//            System.out.println("cur time: " + timeMillis2);
//            break;
//        }
    }
}
