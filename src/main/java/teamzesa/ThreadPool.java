package teamzesa;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ThreadPool {
    private static class ThreadPoolHolder {
        private static final ThreadPool INSTANCE = new ThreadPool();
    }

    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    public static ThreadPool getThreadPool() {
        return ThreadPoolHolder.INSTANCE;
    }

    private ThreadPool() {
        executorService = Executors.newFixedThreadPool(4);
        scheduledExecutorService = Executors.newScheduledThreadPool(4);
    }

    public void addTask(Runnable task) {
        executorService.submit(task);
    }

    public void addSchedulingTask(Runnable task, long delay , long interval) {
        scheduledExecutorService.scheduleWithFixedDelay(task,delay,interval,TimeUnit.SECONDS);
    }

    public void executorServiceOff() {
        executorService.shutdown();
    }

    public void serviceOff() {
        executorService.shutdownNow();
        scheduledExecutorService.shutdownNow();
    }
}
