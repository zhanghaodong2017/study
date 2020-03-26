package com.zhd.study.oom;

import com.zhd.study.proxy.CglibProxy;
import com.zhd.study.proxy.UserManagerImpl;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-02-01 20:20
 */
public class CglibBean {
    private String className;

    public CglibBean(String className) {
        this.className = className;
        CglibProxy cglib = new CglibProxy();//实例化CglibProxy对象
        UserManagerImpl user2 = (UserManagerImpl) cglib.getCglibProxy(new UserManagerImpl());//获取代理对象
        user2.delUser("admin");
    }
}
