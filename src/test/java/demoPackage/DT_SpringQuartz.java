package demoPackage;

import demoClass.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class DT_SpringQuartz {

    public static void main(String[] args) throws SchedulerException {
        //获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义任务调度实例，并与TestJob绑定
        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("testJob","testJobGroup").build();
        //定义触发器，会马上触发一次，每5秒触发一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger","testTriggerGroup").startNow().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();
        //使用触发器调度任务的执行
        scheduler.scheduleJob(job,trigger);
        //开启任务
        scheduler.start();
    }
}
