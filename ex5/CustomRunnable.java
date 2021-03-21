package by.itacademy.lesson16.ex5;

public class CustomRunnable implements Runnable{

    private int second;

    public CustomRunnable(int second) {
        this.second = second;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(second * 1000L);
            System.out.println("Я спал " + second + " секунд");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
