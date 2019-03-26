package com.kuan.demo.Utils;

import java.util.concurrent.atomic.AtomicInteger;

public class TreadIncrement {
    public static void main(String[] args) {
        Tr tr = new Tr();
        new Thread(tr, "A").start();
        new Thread(tr, "B").start();
        new Thread(tr, "C").start();
        new Thread(tr, "D").start();
        new Thread(tr, "E").start();
        new Thread(tr, "F").start();
    }
}

class Tr implements Runnable {
    private static final AtomicInteger atomicNum = new AtomicInteger();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int num = atomicNum.incrementAndGet();
            String str = String.format("%04d", num);
            System.out.println(Thread.currentThread().getName() + "\t" + str);
        }
    }
}
