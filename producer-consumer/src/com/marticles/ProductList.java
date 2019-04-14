package com.marticles;

/**
 * @author Marticles
 * @description ProductList
 * @date 2019/4/14
 */
public class ProductList {

    // 当前容量
    private int size = 0;
    // 最大容量
    private static final int MAX_SIZE = 5;

    public synchronized void put() throws InterruptedException {
        // 如果容量小于最大容量，通知生产者生产
        if (size < MAX_SIZE){
            System.out.println("生产者生产了一个产品，当前容量："+ ++size);
            notifyAll();
        // 如果容量大于最大容量，通知生产者等待
        }else{
            System.out.println("容量达到最大，当前容量："+ size);
            wait();
        }
    }

    public synchronized void get() throws InterruptedException {
        // 如果容量大于0，通知消费者消费
        if (size > 0){
            System.out.println("消费者消费了一个产品，当前容量："+ --size);
            notifyAll();
        // 如果容量小于0，通知消费者等待
        }else{
            System.out.println("容量已为0");
            wait();
        }
    }

}
