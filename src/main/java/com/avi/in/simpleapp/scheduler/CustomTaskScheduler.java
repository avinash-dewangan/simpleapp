package com.avi.in.simpleapp.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class CustomTaskScheduler {

    // Scheduled task to run every 10 seconds
    @Scheduled(fixedRate = 10000)  // Fixed rate of 10 seconds (in milliseconds)
    public void runTaskAtFixedRate() {
        System.out.println("Task running at fixed rate: " + LocalDateTime.now());
    }

    // Scheduled task to run 5 seconds after the previous task's completion
    @Scheduled(fixedDelay = 5000)  // Fixed delay of 5 seconds (in milliseconds) after the previous task finishes
    public void runTaskWithFixedDelay() {
        System.out.println("Task running with fixed delay: " + LocalDateTime.now());
    }

    // Scheduled task using a cron expression (runs every minute at the 0th second)
    @Scheduled(cron = "0 * * * * *")  // Cron expression: "0 * * * * *" means every minute at 0th second
    public void runTaskUsingCronExpression() {
        System.out.println("Task running using cron expression: " + LocalDateTime.now());
    }

    // Task that simulates a long-running task (2 seconds) for demonstration purposes
    @Scheduled(fixedRate = 15000)  // This task will run every 15 seconds
    public void longRunningTask() throws InterruptedException {
        System.out.println("Long-running task started at: " + LocalDateTime.now());
        TimeUnit.SECONDS.sleep(2);  // Simulate a 2-second delay
        System.out.println("Long-running task finished at: " + LocalDateTime.now());
    }
}
