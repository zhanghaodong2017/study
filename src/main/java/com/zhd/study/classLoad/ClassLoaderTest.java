package com.zhd.study.classLoad;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 12:26
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new MyClassLoader();
        Object obj = myLoader.loadClass("com.zhd.study.classLoad.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);

        Object obj2 = new MyTwoClassLoader().loadClass("com.zhd.study.classLoad.ClassLoaderTest").newInstance();
        System.out.println(obj2.getClass());
        System.out.println(obj2 instanceof ClassLoaderTest);


        Object obj3 = ClassLoaderTest.class.getClassLoader().loadClass("com.zhd.study.classLoad.ClassLoaderTest").newInstance();
        System.out.println(obj3.getClass());
        System.out.println(obj3 instanceof ClassLoaderTest);


        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        System.out.println(classLoaderTest.getClass());
        System.out.println(classLoaderTest instanceof ClassLoaderTest);

        System.out.println("---------------");
        System.out.println(obj2.getClass() == obj.getClass());
        System.out.println(classLoaderTest.getClass() == obj3.getClass());
        System.out.println(obj2.getClass() == obj3.getClass());
        System.out.println(obj.getClass() == obj3.getClass());



    }


}
