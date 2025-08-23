package com.zmey.uptime.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;
import com.zmey.uptime.entities.Target;
import com.zmey.uptime.entities.enums.Protocol;
import com.zmey.uptime.quartz.jobs.PingJob;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JobFactory {
    public JobFactory() {

    }

    public Class<? extends Job> getJobClass(Target savedTarget) {
        if (savedTarget.getProtocol().equals(Protocol.HTTP)) {
            return HttpJob.class;
        }
        return PingJob.class;
    }

    public JobDetail getJob(Target savedTarget) {
        JobDetail job = buildJobDetail(PingJob.class);
        return job;
    }

    private JobDetail buildJobDetail(Class<? extends Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getSimpleName(), "monitoringGroup")
                .storeDurably()
                .requestRecovery()
                .build();
    }

}
