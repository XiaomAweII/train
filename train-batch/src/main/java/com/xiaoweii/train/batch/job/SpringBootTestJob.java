package com.xiaoweii.train.batch.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/*
* 自带的定时任务只适合单体应用, 不适合集群
* 没法实时更改定时任务状态和策略
* */
@Component
@EnableScheduling//开启任务类, 增加@EnableScheduling使得该类变成一个任务类
public class SpringBootTestJob {

    // @Scheduled添加定时任务策略,cron表达式
    // 简单了解, 反正我没记住, 会看就行, cron从左到右(用空格隔开): 秒 分 小时 月份中的日期 月份 星期中的日期 年份
    // 可以使用在线cron表达式
    // 这里的秒0/5 表示 当前描述除以5, 余数是0 , 就触发
    @Scheduled(cron="0/5 * * * * ?")
    private void test(){
        // 增加分布式锁, 解决集群问题
        System.out.println("SpringBootTestJob TEST");
    }
}
