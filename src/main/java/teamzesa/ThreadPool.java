package teamzesa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static class ThreadPoolHolder {
        private static final ThreadPool INSTANCE = new ThreadPool();
    }

    private final ExecutorService executorService;

    public static ThreadPool getThreadPool() {
        return ThreadPoolHolder.INSTANCE;
    }

    private ThreadPool() {
        executorService = Executors.newFixedThreadPool(1000);
    }

    public void addTask(Runnable task) {
        executorService.submit(task);
    }
}
