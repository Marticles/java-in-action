package com.marticles;

/**
 * @author Marticles
 * @description Consumer
 * @date 2019/4/14
 */
public class Consumer implements Runnable{


    private ProductList productList;

    public Consumer(ProductList productList){
        this.productList = productList;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100L);
                productList.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
