package com.zmey.uptime.quartz;

import org.apache.commons.lang3.NotImplementedException;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmey.uptime.entities.Target;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JobManager {
    @Autowired
    UptimeJobFactory jobFactory;

    @Autowired
    SchedulerManager scheduler;

    public void scheduleJob(Target savedTarget) {
        JobDetail jobDetail = buildJobDetail(jobFactory.getJobClass(savedTarget));
        Trigger trigger = buildJobTrigger(jobDetail);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void removeJob() {
        scheduler.stopJob(null, null);
    }

    public void pauseJob() {
        // scheduler.pauseJob(null, null);
        throw new NotImplementedException("not implemented yet");
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getSimpleName(), "monitoringGroup")
                .storeDurably()
                .requestRecovery()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail job) {
        log.error("job key = " + job.getKey());
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
