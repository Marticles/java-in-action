package com.marticles.jdk.dynamicproxy2;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marticles
 * @date 2019/1/10
 */
public class Main {

    public static void main(String[] args) {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[] { Map.class },
                new DynamicInvocationHandler(new HashMap<String, Method>()));

        mapProxyInstance.put("Hello", "World");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[] { CharSequence.class },
                new DynamicInvocationHandler("Hello World"));

        csProxyInstance.length();

        List listProxyInstance = (List) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[] { List.class },
                new DynamicInvocationHandler(new ArrayList<String>()));

        listProxyInstance.size();
    }
}
