package com.zmey.uptime.quartz.jobs;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
@DisallowConcurrentExecution
public class PingJob extends QuartzJobBean {

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Trigger trigger = context.getTrigger();
        Date nextFireTime = trigger.getNextFireTime();
        // log.info("PingJob executed with context " + context );
        // log.info("PingJob executed with jobDataMap " + jobDataMap);
        log.info("PingJob executed with nextFireTime " + nextFireTime);
        jobDataMap.forEach((k, v) -> {
            log.info("k= " + k + " v= " + v);
        });
    }
}
