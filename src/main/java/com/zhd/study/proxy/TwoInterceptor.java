package com.zhd.study.proxy;

import java.util.Properties;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 16:58
 */
public class TwoInterceptor implements MyInterceptor {

    @Override
    public Object intercept(MyInvocation invocation) throws Throwable {
        System.out.println("TwoInterceptor.before");
        Object proceed = invocation.proceed();
        System.out.println("TwoInterceptor.after");
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
