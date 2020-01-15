package com.zhd.study.proxy;

import java.util.Properties;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 16:52
 */
public interface MyInterceptor {
    Object intercept(MyInvocation invocation) throws Throwable;

    Object plugin(Object target);

    void setProperties(Properties properties);

}
