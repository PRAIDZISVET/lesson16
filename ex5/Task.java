package by.itacademy.lesson16.ex5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
            while (true) {
                int number = scanner.nextInt();
                if (number == -1) {
                    break;
                }
                executorService.execute(new CustomRunnable(number));
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(1L, TimeUnit.HOURS);

    }
}
