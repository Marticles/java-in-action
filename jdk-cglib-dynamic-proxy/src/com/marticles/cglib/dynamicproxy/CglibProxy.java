package com.marticles.cglib.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Marticles
 * @description CglibProxy
 * @date 2019/1/10
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy (Object target){
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before invocation ...");
        Object result = method.invoke(target,objects);
        System.out.println("After invocation ...");
        return result;
    }

    public static Object getProxyInstance(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibProxy(target));
        return enhancer.create();
    }
}
