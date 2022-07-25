package com.example.springdemo;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class JobScheduler {
    private static TaskScheduler scheduler;

    static Runnable remindAlarm = new Runnable() {
        @Override
        public void run() {
            System.out.println("Reminder date has been reached. You still have task pending (提醒日期已到。您仍有待处理的任务)");
        }    };

    static Runnable deadlineAlarm = new Runnable() {
        @Override
        public void run() {
            System.out.println("Deadline has been reached. You still have task pending (截止日期已到。您仍有待处理的任务)");
        }
    };

    @Async
    public ScheduledFuture setAlarm(Date date) {
        ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduler = new ConcurrentTaskScheduler(localExecutor);

        ScheduledFuture scheduledFuture;
        scheduledFuture = scheduler.schedule(remindAlarm, date);
        return scheduledFuture;
    }
}