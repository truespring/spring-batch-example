package com.example.batchproject.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class SchedulerExample {
    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(fixedDelay = 30000)
    public void startJob() {
        try {
            Map<String, JobParameter> jobParameterMap = new HashMap<>();
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            jobParameterMap.put("requestDate", new JobParameter(time));
            JobParameters parameters = new JobParameters(jobParameterMap);
            JobExecution jobExecution = jobLauncher.run(job, parameters);

            while(jobExecution.isRunning()) {
                log.info("isRunning...");
            }
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

}
