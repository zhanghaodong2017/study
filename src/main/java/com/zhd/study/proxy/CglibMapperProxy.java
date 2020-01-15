package com.zhd.study.proxy;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 10:52
 */
public class CglibMapperProxy<T> implements MethodInterceptor {

    private Class<T> clazz;

    public CglibMapperProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy methodProxy) throws Throwable {
        System.out.println(JSON.toJSONString(obj));
        System.out.println(method.getName());
        System.out.println(JSON.toJSONString(arr));
        System.out.println(Arrays.toString(methodProxy.getSignature().getArgumentTypes()));
        System.out.println(methodProxy.getSignature().getName());
        System.out.println(clazz.getName() + "-" + method.getName());
        return "ok";
    }

    public T getCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

}
