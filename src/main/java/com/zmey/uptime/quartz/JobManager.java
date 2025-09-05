package com.zmey.uptime.quartz;

import org.apache.commons.lang3.NotImplementedException;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.zmey.uptime.entities.Target;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JobManager {
    @Autowired
    UptimeJobFactory jobFactory;

    // @Autowired
    // SchedulerManager scheduler;
    private final Scheduler scheduler;

    public JobManager(SchedulerFactoryBean schedulerFactory) {
        this.scheduler = schedulerFactory.getScheduler();
    }

    public void scheduleJob(Target savedTarget) {
        JobDetail jobDetail = buildJobDetail(jobFactory.getJobClass(savedTarget));
        Trigger trigger = buildJobTrigger(jobDetail);
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("error while runing job %s", jobDetail.getDescription());
        }

    }

    public void removeJob() {
        try {
            scheduler.deleteJob(null);
            // scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("error while runing job %s", jobDetail.getDescription());
        }
    }

    public void pauseJob() {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("error while runing job %s", jobDetail.getDescription());
        }
        throw new NotImplementedException("not implemented yet");
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getSimpleName(), "monitoringGroup")
                .withDescription(jobClass.getSimpleName())
                .storeDurably()
                .requestRecovery()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail job) {
        log.error("job key = " + job.getKey());
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getDescription(), "monitoringGroup")
                .forJob(job)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();
    }
}
