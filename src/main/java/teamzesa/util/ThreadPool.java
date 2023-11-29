package teamzesa.util;


import java.util.concurrent.*;


public class ThreadPool {
    private static class ThreadPoolHolder {
        private static final ThreadPool INSTANCE = new ThreadPool();
    }

    private final ThreadPoolExecutor executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    public static ThreadPool getThreadPool() {
        return ThreadPoolHolder.INSTANCE;
    }

    private ThreadPool() {
//        executorService = Executors.newFixedThreadPool(4);
        executorService =  new ThreadPoolExecutor(
                5,
                5,
                10,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10)
        );

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
