package com.zhd.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 10:42
 */
public class JdkProxy implements InvocationHandler {
    private Object target;//需要代理的目标对象

    public JdkProxy(Object target) {
        this.target = target;
    }

    public static Object getInstance(Object target) {
        return new JdkProxy(target).getJDKProxy();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result = method.invoke(target, args);
        System.out.println("JDK动态代理，监听结束！");
        System.out.println(proxy.getClass().getName());
        return result;
    }

    //定义获取代理对象方法
    public Object getJDKProxy() {
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
