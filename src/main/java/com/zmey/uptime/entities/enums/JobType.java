package com.zmey.uptime.entities.enums;

import com.zmey.uptime.quartz.jobs.HttpGetJob;
import com.zmey.uptime.quartz.jobs.PingJob;
import org.quartz.Job;

public enum JobType {

    HTTP(HttpGetJob.class),
    ICMP(PingJob.class);

    private final Class<? extends Job> jobClass;

    JobType(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }
}
