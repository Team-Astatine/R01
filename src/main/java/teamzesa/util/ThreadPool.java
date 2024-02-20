package teamzesa.util;


import java.util.concurrent.*;


public class ThreadPool {
    private static class ThreadPoolHolder {
        private static final ThreadPool INSTANCE = new ThreadPool();
    }

    public static ThreadPool getThreadPool() {
        return ThreadPoolHolder.INSTANCE;
    }


    private ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

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
        scheduledExecutorService = Executors.newScheduledThreadPool(4);
    }

    public void addTask(Runnable task) {
        this.executorService = new ThreadPoolExecutor(
                2,
                4,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
        executorService.submit(task);
    }

    public void addSchedulingTaskMin(Runnable task, long delay , long interval) {
        scheduledExecutorService.scheduleWithFixedDelay(task,delay,interval,TimeUnit.MINUTES);
    }

    public synchronized void addSchedulingTaskSec(Runnable task, long delay , long interval) {
        scheduledExecutorService.scheduleWithFixedDelay(task,delay,interval,TimeUnit.SECONDS);
    }

    public void executorServiceOff() {
        executorService.shutdown();
    }

    public void allServiceOff() {
//        executorService.shutdownNow();
        scheduledExecutorService.shutdownNow();
    }
}