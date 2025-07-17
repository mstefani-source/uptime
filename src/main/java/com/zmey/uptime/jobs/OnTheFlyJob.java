//package com.zmey.uptime.jobs;
//
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.RandomUtils;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Random;
//
//@Component
//@Slf4j
//@DisallowConcurrentExecution
//public class OnTheFlyJob extends QuartzJobBean {
//
//    @Autowired
//    SchedulerFactoryBean schedulerFactoryBean;
//
//    @Override
//    protected void executeInternal(@NonNull JobExecutionContext context) {
//        // This is how we can get the JobDataMap from the JobExecutionContext
//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//
//        // Here goes our task logic that we want to get processed
//        // when the job gets executed
//    }
//
//    // This method can be called from a client code
//    // who wants to create and schedule the job
//    public void start() {
//        try {
//            Scheduler timer = schedulerFactoryBean.getScheduler();
//            // The Scheduler starts listening to job triggers
//            // after we start() the Scheduler
//            timer.start();
//
//            // We can use the JobDataMap to add any kind of data
//            // that we might need during the job execution
//            JobDataMap jobDataMap = new JobDataMap();
//            jobDataMap.put("key", "value");
//
//            JobDetail jobDetail =
//                    JobBuilder.newJob(OnTheFlyJob.class)
//                            .withIdentity(getUniqueJobName("onTheFlyJob"))
//                            .storeDurably()
//                            .requestRecovery()
//                            .usingJobData(jobDataMap)
//                            .build();
//
//            // storeDurably() persists the job data in the database
//            // requestRecovery() recovers job execution if it fails in the first place for some reason
//
//            Date scheduledAt = new Date();
//
//            Trigger jobTrigger =
//                    TriggerBuilder.newTrigger()
//                            .forJob(jobDetail)
//                            .startAt(scheduledAt)
//                            .withIdentity("onTheFlyJobTrigger")
//                            .build();
//
//            // The job is scheduled to start at a specified time.
//
//            // Here we submit the job detail and the trigger to the scheduler
//            timer.scheduleJob(jobDetail, jobTrigger);
//
////            return jobDetail;
//        } catch(ObjectAlreadyExistsException e) {
//            // We have disallowed concurrent job execution
//            // so if we try to run a job with the same identity
//            // Quartz will throw this exception
//        } catch (SchedulerException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private String getUniqueJobName(String suffix) {
//        Random random = new Random();
//        int minVal = 1;
//        int maxVal = 1000000;
//        int randomNumber = random.nextInt((maxVal - minVal)+1) + minVal;
//        String identifier = String.valueOf(randomNumber);
//        // We can make use of random number as our identifier
//        // to uniquely identify our jobs.
//        // Or, we can use some relevant ID as our identifier.
//        return identifier + "-" + suffix;
//    }
//}
