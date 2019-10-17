package com.happy.syn;

/**
 *  死锁demo
 */
public class DeadLockDemo {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (LOCK_A){
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_B){
                    System.out.println("A 尝试去获取B的锁");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (LOCK_B){
                synchronized (LOCK_A){
                    System.out.println("B 尝试获取A的锁");
                }
            }
        }).start();
    }
}
