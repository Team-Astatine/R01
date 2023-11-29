package teamzesa.util;


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
        /*
//        https://engineerinsight.tistory.com/197
//        executorService = Executors.newFixedThreadPool(4);
        executorService =  new ThreadPoolExecutor(
                CORE_POOL_SIZE, //기본 thread core 1개 유지
                MAX_CORE_POOL_SIZE,  // 최대 쓰레딩 wait 갯수 4
                KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY)
//                new SynchronousQueue<>()
        );
        */
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

    public void allServiceOff() {
        executorService.shutdownNow();
        scheduledExecutorService.shutdownNow();
    }
}