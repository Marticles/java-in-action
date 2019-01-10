package com.marticles.jdk.dynamicproxy;

/**
 * @author Marticles
 * @description RealSubject
 * @date 2019/1/10
 */
public class RealSubject implements Subject{
    @Override
    public void doSomething1(String str) {
        System.out.println("Do something1 .... args:"+str);
    }

    @Override
    public void doSomething2(String str) {
        System.out.println("Do something2 .... args"+str);
    }
}
