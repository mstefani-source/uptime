package com.zmey.uptime.quartz.shedulers;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.zmey.uptime.entities.Target;
import com.zmey.uptime.quartz.jobs.JobFactory;
import com.zmey.uptime.quartz.jobs.PingJob;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JobSheduler {
    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Autowired
    JobFactory jobFactory;

    public void scheduleJob(Target savedTarget) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
  
            JobDetail job = buildJobDetail(jobFactory.getJobClass(savedTarget));
            Trigger trigger = buildJobTrigger(job);

            scheduler.scheduleJob(job, trigger);
            log.info("PingJob scheduled successfully");

        } catch (ObjectAlreadyExistsException e) {
            log.info("Job already exists - skipping rescheduling");
        } catch (SchedulerException e) {
            log.error("Scheduling failed", e);
            throw new RuntimeException("Failed to schedule PingJob", e);
        }
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getSimpleName(), "monitoringGroup")
                .storeDurably()
                .requestRecovery()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .withIdentity("pingTrigger", "monitoringGroup")
                .forJob(job)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();
    }
}
