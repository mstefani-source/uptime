package com.zmey.uptime.jobs;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
@DisallowConcurrentExecution
public class PingJob extends QuartzJobBean {

//    SchedulerFactory schedulerFactory = new StdSchedulerFactory();


    @Autowired
    SchedulerFactoryBean schedulerFactory;
//    Scheduler scheduler = SchedulerFactoryBean.getScheduler();

    public void start() {
        try {
            log.info("scheduler has start");
            Scheduler scheduler = schedulerFactory.getScheduler();

            scheduler.start();

            JobDetail job = JobBuilder.newJob(PingJob.class)
                    .withIdentity("myJob", "group1")
                    .storeDurably()
                    .requestRecovery()
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .forJob("myJob", "group1")
                    .build();

            scheduler.scheduleJob(job, trigger);

        } catch(ObjectAlreadyExistsException e) {
            // We have disallowed concurrent job execution
            // so if we try to run a job with the same identity
            // Quartz will throw this exception
            log.info("ObjectAlreadyExistsException caught");
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Trigger trigger = context.getTrigger();
        Date nextFireTime = trigger.getNextFireTime();
        log.info("PingJob executed with context " + context);


    }
}

