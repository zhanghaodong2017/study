package com.zhd.study.proxy;

import java.util.Properties;

public class OneInterceptor implements MyInterceptor {

    @Override
    public Object intercept(MyInvocation invocation) throws Throwable {
        System.out.println("OneInterceptor.before");
        Object proceed = invocation.proceed();
        System.out.println("OneInterceptor.after");
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return MyPlugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
