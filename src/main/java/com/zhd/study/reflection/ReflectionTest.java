package com.zhd.study.reflection;

import com.zhd.study.proxy.UserManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-15 11:17
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.zhd.ultimate.sociology.proxy.UserManagerImpl");
        //创建对象
        Object instance = clazz.newInstance();
        if (instance instanceof UserManager) {//内省
            UserManager userManager = (UserManager) instance;
            userManager.doWork("zhangsan");
        }
        //调用未知对象的方法
        Method method = clazz.getMethod("addUser", String.class, String.class);
        method.invoke(instance, "zhangsan", "123");

        //获取构造函数，并创建对象。
        Constructor<?>[] constructors = clazz.getConstructors();
        Object instance1 = constructors[0].newInstance();
        if (instance1 instanceof UserManager) {//内省
            UserManager userManager1 = (UserManager) instance1;
            userManager1.delUser("zhangsan");
        }
    }
}
