package by.itacademy.lesson16.ex4;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CycleBarrierDemo {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        RocketPart[] rocketParts = RocketPart.values();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(rocketParts.length, new Runnable() {
            @Override
            public void run() {
                System.out.println("Рокета пошла!");
            }
        });
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (RocketPart rocketPart : rocketParts) {
            executorService.execute(new RocketPartRunnable(cyclicBarrier, rocketPart));
        }
        System.out.println("OK");

        executorService.shutdown();
        executorService.awaitTermination(1L, TimeUnit.HOURS);
    }

    public static class RocketPartRunnable implements Runnable {

        private final CyclicBarrier cyclicBarrier;
        private final RocketPart rocketPart;

        public RocketPartRunnable(CyclicBarrier cyclicBarrier, RocketPart rocketPart) {
            this.cyclicBarrier = cyclicBarrier;
            this.rocketPart = rocketPart;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName());
                int waitingTime = RANDOM.nextInt(10) + 5;
                Thread.sleep(waitingTime * 1000L);
                System.out.println(rocketPart + " готова. Время готовки: " + waitingTime);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
