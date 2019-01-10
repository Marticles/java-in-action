package com.marticles.cglib.dynamicproxy;

public class Main {

    public static void main(String[] args) {
        ProxyClass proxyClass = (ProxyClass) CglibProxy.getProxyInstance(new ProxyClass());
        proxyClass.doSomething();
        proxyClass.finalDoSomething();
    }
}
