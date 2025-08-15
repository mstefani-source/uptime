package com.zmey.uptime.quartz.jobs;

import org.quartz.Trigger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.lang.NonNull;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpGetJob extends QuartzJobBean {
    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
       JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
       Trigger trigger = context.getTrigger();
       log.info("HttpGetJob executed with jobDataMap {} and trigger {}", jobDataMap, trigger); 
    }        
}
