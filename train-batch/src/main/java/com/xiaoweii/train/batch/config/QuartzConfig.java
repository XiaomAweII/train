package com.xiaoweii.train.batch.config;// package com.jiawa.train.batch.config;

 import com.xiaoweii.train.batch.job.TestJob;
 import org.quartz.*;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 @Configuration
 public class QuartzConfig {

     /**
      * 声明一个任务
      * @return
      */
     @Bean
     public JobDetail jobDetail() {
         return JobBuilder.newJob(TestJob.class)
                 .withIdentity("TestJob", "test") //都可以自定义, 不同的组还有name
                 .storeDurably()
                 .build();
     }

     /**
      * 声明一个触发器，什么时候触发这个任务
      * @return
      */
     @Bean
     public Trigger trigger() {
         return TriggerBuilder.newTrigger()// new 一个触发器, 然后起名再分组
                 .forJob(jobDetail())
                 .withIdentity("trigger", "trigger")
                 .startNow()
                 .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                 .build();
     }
 }
