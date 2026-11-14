package com.multithreading;


//Approach 1 Using Thread Class


public class EvenOrOddPrinterByThread implements Runnable {

    static int count = 1;
    Object object;

    public EvenOrOddPrinterByThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        while (count <= 50) {
            if (count % 2 == 0 && Thread.currentThread().getName().equals("even")) {
                synchronized (object) {
                    System.out.println("Thread name: " + Thread.currentThread().getName() + " value " + count);
                    count++;
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (count % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
                synchronized (object) {
                    System.out.println("Thread name: " + Thread.currentThread().getName() + " value " + count);
                    count++;
                    object.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        Runnable runnable = new EvenOrOddPrinterByThread(lock);
        Runnable runnable2 = new EvenOrOddPrinterByThread(lock);
        new Thread(runnable,"even").start();
        new Thread(runnable2,"odd").start();
    }
}
