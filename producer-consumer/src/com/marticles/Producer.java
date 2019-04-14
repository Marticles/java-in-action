package com.marticles;

/**
 * @author Marticles
 * @description Producer
 * @date 2019/4/14
 */
public class Producer implements Runnable{

    private ProductList productList;

    public Producer(ProductList productList){
        this.productList = productList;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100L);
                productList.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
