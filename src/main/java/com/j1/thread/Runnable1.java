package com.j1.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runnable1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new TestRunnable());
            System.out.println("************* a" + i + " *************");
        }
        executorService.shutdown();
    }
    public static class TestRunnable implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程被调用了。");
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

