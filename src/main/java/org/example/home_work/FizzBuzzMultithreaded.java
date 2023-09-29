package org.example.home_work;

import java.util.concurrent.*;

public class FizzBuzzMultithreaded {
    private int n;
    private volatile int counter;
    protected BlockingQueue<String> queue;
    private ExecutorService executorService;
    private CountDownLatch latch;

    public FizzBuzzMultithreaded(int n) {
        this.n = n;
        this.counter = 1;
        this.queue = new LinkedBlockingQueue<>();
        this.executorService = Executors.newFixedThreadPool(4);
        this.latch = new CountDownLatch(4);
    }

    public void fizz() {
        executorService.submit(() -> {
            while (counter <= n) {
                synchronized (this) {
                    if (counter % 3 == 0 && counter % 5 != 0) {
                        queue.add("Fizz");
                        counter++;
                    }
                }
            }
            latch.countDown();
        });
    }

    public void buzz() {
        executorService.submit(() -> {
            while (counter <= n) {
                synchronized (this) {
                    if (counter % 5 == 0 && counter % 3 != 0) {
                        queue.add("Buzz");
                        counter++;
                    }
                }
            }
            latch.countDown();
        });
    }

    public void fizzbuzz() {
        executorService.submit(() -> {
            while (counter <= n) {
                synchronized (this) {
                    if (counter % 3 == 0 && counter % 5 == 0) {
                        queue.add("FizzBuzz");
                        counter++;
                    }
                }
            }
            latch.countDown();
        });
    }

    public void number() {
        executorService.submit(() -> {
            while (counter < n) {
                synchronized (this) {
                    if (counter % 3 != 0 && counter != 0) {
                        queue.add(String.valueOf(counter));
                        counter++;
                    }
                }
            }
            latch.countDown();
        });
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) {
        int n = 15;
        FizzBuzzMultithreaded fizzBuzz = new FizzBuzzMultithreaded(n);
        fizzBuzz.fizz();
        fizzBuzz.buzz();
        fizzBuzz.fizzbuzz();
        fizzBuzz.number();

        fizzBuzz.shutdown();

        while (!fizzBuzz.queue.isEmpty()) {
            try {
                System.out.println(fizzBuzz.queue.take());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

