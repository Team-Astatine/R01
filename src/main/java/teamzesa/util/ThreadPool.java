package teamzesa.util;


import java.util.concurrent.*;


public class ThreadPool {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    private static final int QUEUE_CAPACITY = 10;
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    private static class ThreadPoolHolder {
        private static final ThreadPool INSTANCE = new ThreadPool();
    }

    public static ThreadPool getThreadPool() {
        return ThreadPoolHolder.INSTANCE;
    }

    private ThreadPool() {
//        https://engineerinsight.tistory.com/197
//        executorService = Executors.newFixedThreadPool(4);
        executorService =  new ThreadPoolExecutor(
                1, //기본 thread core 1개 유지
                Integer.MAX_VALUE,  // 최대 쓰레딩 wait 갯수 21억 ㅋ
                10L, TimeUnit.SECONDS,
                new SynchronousQueue<>());

        scheduledExecutorService = Executors.newScheduledThreadPool(5);
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
