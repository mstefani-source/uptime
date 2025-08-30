package com.zmey.uptime.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SchedulerManager {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerManager(SchedulerFactoryBean schedulerFactory) {
        this.scheduler = schedulerFactory.getScheduler();
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("Scheduling failed", e);
            throw new RuntimeException("Failed to run schedule", e);
        }
    }

    public void shutdownSheduler() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error("Scheduling shotdown failed", e);
            throw new RuntimeException("Failed to shutdown schedule", e);
        }
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("Scheduling failed", e);
            e.printStackTrace();
        }
    }

    public boolean stopJob(String jobName, String jobGroup) {

        return false;
    }
}
