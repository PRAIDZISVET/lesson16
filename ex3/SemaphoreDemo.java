package by.itacademy.lesson16.ex3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executorService.execute(new CounterRunnable(semaphore));
        }

        executorService.shutdown();
        executorService.awaitTermination(1L, TimeUnit.HOURS);


    }
    public static class CounterRunnable implements Runnable {

        private final Semaphore semaphore;

        public CounterRunnable(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5000; i++) {
                    semaphore.acquire();
                    System.out.println(++counter);
                    semaphore.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
