package com.zhd.study.linklist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-10 16:57
 */
public class ArrayListTest {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.add("k-" + i);
        }
        Class<?> aClass = Class.forName("java.util.ArrayList");

        Field field = aClass.getDeclaredField("elementData");
        field.setAccessible(true);

        Object[] obj = (Object[]) field.get(list);
        System.out.println(Arrays.toString(obj));

        System.out.println(list);
    }
}
