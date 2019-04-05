package com.marticles.cglib.dynamicproxy;

/**
 * @author Marticles
 * @description TestProxyClass
 * @date 2019/1/10
 */
public class TestProxyClass {
    public void doSomething(){
        System.out.println("Do somthing ....");
    }


    // final方法不会被重写
    public final void finalDoSomething(){
        System.out.println("Final do somthing ....");
    }

}
