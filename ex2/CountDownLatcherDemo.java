package by.itacademy.lesson16.ex2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatcherDemo {

    public static final int TIMER = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(TIMER);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new RocketStart(countDownLatch));
        executorService.execute(new CountDownRunnable(countDownLatch));

        executorService.shutdown();
        executorService.awaitTermination(1L, TimeUnit.HOURS);
    }

    public static class RocketStart implements Runnable {

        private final CountDownLatch countDownLatch;

        public RocketStart(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println("Ракета запущена!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CountDownRunnable implements Runnable {

        private final CountDownLatch countDownLatch;

        public CountDownRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }


        @Override
        public void run() {
            for (int i = TIMER; i > 0; i--) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                countDownLatch.countDown();
            }
        }
    }

}
