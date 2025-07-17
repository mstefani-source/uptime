package com.zmey.uptime.jobs;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@DisallowConcurrentExecution
public class PingJob extends QuartzJobBean {

//    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//    Scheduler scheduler = schedulerFactory.getScheduler();

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public void start() {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.start();

            JobDetail job = JobBuilder.newJob(PingJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);

        } catch(ObjectAlreadyExistsException e) {
            // We have disallowed concurrent job execution
            // so if we try to run a job with the same identity
            // Quartz will throw this exception
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
       log.info("PingJob executed");
    }
}
