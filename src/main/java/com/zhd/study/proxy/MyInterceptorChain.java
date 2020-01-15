package com.zhd.study.proxy;

import java.util.ArrayList;
import java.util.List;


public class MyInterceptorChain {

    private final List<MyInterceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target) {
        for (MyInterceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(MyInterceptor interceptor) {
        interceptors.add(interceptor);
    }


}
