package 并发编程.线程池;

import java.util.concurrent.*;

public class T06_Future {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get());//get 阻塞

/*************************************************************************************/
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future<Integer> future = es.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });

        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());

        es.shutdown();

    }
}
