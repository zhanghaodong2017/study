package com.zhd.study.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 10:45
 */
public class ProxyTest {


    public static void main(String[] args) {
        //输出代理class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        doProxy();
        writeClassToDisk("/Users/zhanghaodong/work/myproject/sociology/target/$Proxy0.class");

    }

    private static void doProxy() {
        try {
            /*UserManager user1 = (UserManager) JdkProxy.getInstance(new UserManagerImpl());//获取代理对象

            UserManager user2 = (UserManager) JdkProxy.getInstance(user1);//获取代理对象

            user2.doWork("admin");*/

            JdkProxy jdkProxy = new JdkProxy(new UserManagerImpl());//实例化JDKProxy对象
            UserManager user = (UserManager) jdkProxy.getJDKProxy();//获取代理对象
            user.addUser("admin", "123123");//执行新增方法
            user.delUser("admin");


            /*CglibProxy cglib = new CglibProxy();//实例化CglibProxy对象
            UserManagerImpl user2 = (UserManagerImpl) cglib.getCglibProxy(new UserManagerImpl());//获取代理对象
            user2.delUser("admin");//执行删除方法

            CglibMapperProxy<UserManager> cglibMapperProxy = new CglibMapperProxy(UserManager.class);
            UserManager userManager = cglibMapperProxy.getCglibProxy();
            String result = userManager.doWork("admin");
            System.out.println(result);*/

           /* Object userManager1 = MyPlugin.wrap(new UserManagerImpl(), new OneInterceptor());
            UserManager userManager2 = (UserManager) MyPlugin.wrap(userManager1, new TwoInterceptor());
            String result = userManager2.doWork("zhangsan");
            System.out.println(result);


            MyInterceptorChain chain = new MyInterceptorChain();
            chain.addInterceptor(new OneInterceptor());
            chain.addInterceptor(new TwoInterceptor());

            UserManager userManager3 = (UserManager) chain.pluginAll(new UserManagerImpl());
            String result2 = userManager3.doWork("zhangsan");
            System.out.println(result2);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeClassToDisk(String path) {
        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy0", new Class[]{UserManager.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
