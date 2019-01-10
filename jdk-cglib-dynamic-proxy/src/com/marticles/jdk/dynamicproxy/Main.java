package com.marticles.jdk.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理所用到的代理类在程序调用到代理类对象时才由JVM真正创建
 * 原理是通过反射创建class并动态生成类字节码加载到JVM中
 * @author Marticles
 * @date 2019/1/10
 */
public class Main {

    public static void main(String[] args) {
        Subject proxySubject = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Subject.class},
                new DynamicInvocationHandler(new RealSubject()));
        proxySubject.doSomething1("test1");
        proxySubject.doSomething1("test2");
    }
}
