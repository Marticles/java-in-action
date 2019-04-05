package com.marticles.jdk.dynamicproxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Marticles
 * @description DynamicInvocationHandler
 * @date 2019/1/10
 */
public class DynamicInvocationHandler implements InvocationHandler {

    private final HashMap<String, Method> methods = new HashMap<String, Method>();

    private Object target;

    public DynamicInvocationHandler(Object target) {
        this.target = target;

        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;
        System.out.println("Executing " + method.getName() + " finished in " + elapsed + " ns");
        return result;
    }
}
