package com.re_teraction.backend.global.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class DelayedScheduler implements Scheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    private static final int CORE_POOL_SIZE = 2;
    private static final int INITIAL_DELAY = 0;
    private static final int DELAY = 1;

    @Override
    public void execute(Runnable task) {
        scheduler.scheduleWithFixedDelay(task, INITIAL_DELAY, DELAY, TimeUnit.DAYS);
    }
}
