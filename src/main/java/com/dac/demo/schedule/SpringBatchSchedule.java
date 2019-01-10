package com.dac.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class SpringBatchSchedule {

    private static final Logger log = LoggerFactory.getLogger(SpringBatchSchedule.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("importCustomer")
    private Job firstScheduleJob;

    @Autowired
    @Qualifier("exportCustomer")
    private Job secondScheduleJob;

    @Scheduled(fixedDelayString = "${spring.schedule.delay}")
    public void launchJob() {
        log.info("Start schedule");
        try {
            JobExecution execution = jobLauncher.run(firstScheduleJob, new JobParametersBuilder()
                    .addString("message", "Import job start")
                    .addDate("date", new Date())
                    .toJobParameters());
            if (execution.getStatus() == BatchStatus.COMPLETED) {
                log.info("!!! JOB Import Customer FINISHED! Time to verify the results");
                if (execution.getJobInstance().getJobName().equals("importCustomer")) {
                    execution.stop();
                    execution = jobLauncher.run(secondScheduleJob, new JobParametersBuilder()
                            .addString("message", "Export job start")
                            .addDate("date", new Date())
                            .toJobParameters());
                }
                if (execution.getJobInstance().getJobName().equals("exportCustomer")) {
                    execution.stop();
                    execution = jobLauncher.run(firstScheduleJob, new JobParametersBuilder()
                            .addString("message", "Import job start")
                            .addDate("date", new Date())
                            .toJobParameters());
                }
            }
        } catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
            log.error(e.getMessage());
        }
        log.info("End schedule");
    }

}
