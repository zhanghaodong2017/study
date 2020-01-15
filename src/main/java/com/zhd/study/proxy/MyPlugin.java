package com.zhd.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;


public class MyPlugin implements InvocationHandler {
    private Object target;
    private MyInterceptor interceptor;

    public MyPlugin(Object target, MyInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object wrap(Object target, MyInterceptor interceptor) {
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type);
        if (interfaces.length > 0) {
            return Proxy.newProxyInstance(
                    type.getClassLoader(),
                    interfaces,
                    new MyPlugin(target, interceptor));
        }
        return target;
    }

    private static Class<?>[] getAllInterfaces(Class<?> type) {
        Set<Class<?>> interfaces = new HashSet<>();
        while (type != null) {
            for (Class<?> c : type.getInterfaces()) {
                interfaces.add(c);
            }
            type = type.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return interceptor.intercept(new MyInvocation(target, method, args));
    }
}
