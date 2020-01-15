package com.zhd.study.proxy;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 10:42
 */
public class UserManagerImpl implements UserManager {

    public UserManagerImpl() {
        System.out.println("UserManagerImpl构建了");
    }

    @Override
    public void addUser(String userName, String password) {
        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: " + userName + " password: " + password);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("调用了删除的方法！");
        System.out.println("传入参数为 userName: " + userName);
    }

    @Override
    public String doWork(String name) {
        System.out.println("doWork-入参：" + name);
        return name;
    }
}
