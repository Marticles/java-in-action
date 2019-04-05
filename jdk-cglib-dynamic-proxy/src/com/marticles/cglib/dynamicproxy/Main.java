package com.marticles.cglib.dynamicproxy;

public class Main {

    public static void main(String[] args) {
        TestProxyClass proxyClass = (TestProxyClass) CglibProxy.getProxyInstance(new TestProxyClass());
        proxyClass.doSomething();
        proxyClass.finalDoSomething();
    }
}
