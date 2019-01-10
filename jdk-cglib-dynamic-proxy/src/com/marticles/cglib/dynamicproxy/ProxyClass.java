package com.marticles.cglib.dynamicproxy;

/**
 * @author Marticles
 * @description ProxyClass
 * @date 2019/1/10
 */
public class ProxyClass {
    public void doSomething(){
        System.out.println("Do somthing ....");
    }

    // final方法不会被重写
    public final void finalDoSomething(){
        System.out.println("Final do somthing ....");
    }

}
