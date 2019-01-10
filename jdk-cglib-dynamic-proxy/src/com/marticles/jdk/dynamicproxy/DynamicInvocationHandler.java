package com.marticles.jdk.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Marticles
 * @description DynamicInvocationHandler
 * @date 2019/1/10
 */
public class DynamicInvocationHandler implements InvocationHandler {

    Subject realSubject;

    public DynamicInvocationHandler(Subject realSubject){
        this.realSubject = realSubject;
    }

    /**
     * @param proxy 代理类
     * @param method 调用方法
     * @param args 方法参数
     * @return java.lang.Object
     * @author Marticles
     * @date 2019/1/10
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke proxy class....");
        if(method.getName().equals("doSomethin1")){
            method.invoke(realSubject,args);
        }else {
            method.invoke(realSubject,args);
        }
        return null;
    }
}
