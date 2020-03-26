package com.zhd.study.classLoad;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 13:39
 */
public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        if (inputStream == null) {
            return super.loadClass(name);
        }
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
