package org.example.home_work;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task1 {
    public static void main(String[] args) {
        timeDeductionMethod1();
//        timeDeductionMethod2();
    }
    private static void timeDeductionMethod1() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        long time = System.currentTimeMillis();
        executorService.scheduleAtFixedRate(() -> {
            long timeInSecond = (System.currentTimeMillis() - time) / 1000;
            System.out.println("З моменту запуску програми минуло " + timeInSecond + " секунд");

       }, 0, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(() -> {
            long timeInSecond = (System.currentTimeMillis() - time) / 1000;
            System.out.println("Минуло " + timeInSecond + " секунд");
        }, 0, 5, TimeUnit.SECONDS);
    }

    private static void timeDeductionMethod2() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        long time = System.currentTimeMillis();
        Runnable runnable1 = () -> {
            long timeInSecond = (System.currentTimeMillis() - time) / 1000;
            System.out.println("З моменту запуску програми минуло " + timeInSecond + " секунд");
        };
        executorService.scheduleAtFixedRate(runnable1, 0, 1, TimeUnit.SECONDS);

        Runnable runnable2 = () -> {
            long timeInSecond = (System.currentTimeMillis() - time) / 1000;
            System.out.println("Минуло " + timeInSecond + " секунд");
        };
        executorService.scheduleAtFixedRate(runnable2, 0, 5, TimeUnit.SECONDS);
    }
}
