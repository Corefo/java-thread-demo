package com.happy.syn;

import java.util.concurrent.CountDownLatch;

/**
 *  线程不安全示例
 */
public class UnsafeThread {
    private static int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void increatment(){
        num++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    increatment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // 每个线程执行完成后，调用countDownLatch
            countDownLatch.countDown();
        }
        while (true){
            if (countDownLatch.getCount() == 0){
                System.out.println(num);
                break;
            }
        }
    }
}
