package com.marticles;

public class Main {

    public static void main(String[] args) {
        ProductList productList = new ProductList();

        new Thread(new Consumer(productList)).start();
        new Thread(new Consumer(productList)).start();
        new Thread(new Consumer(productList)).start();

        new Thread(new Producer(productList)).start();
        new Thread(new Producer(productList)).start();
        new Thread(new Producer(productList)).start();
        new Thread(new Producer(productList)).start();
        new Thread(new Producer(productList)).start();
    }
}
