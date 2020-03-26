package com.zhd.study.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-02-18 14:17
 */
public class IntTest {
    public static void main(String[] args) {
        int ac = 9;
        // Integer.MAX_VALUE=2147483647;
        Float f = 2147483648.0f;
        System.out.println(f.intValue());
        System.out.println(-0f);
        Double d = 0x1.ffffffeP+127d;
        Double d2 = 0x1.ffffffeP-200d;
        System.out.println(d.floatValue());
        System.out.println(d2.floatValue());
        System.out.println(Float.MIN_VALUE);

        System.out.println(~(-8));
        System.out.println((-1) ^ (-8));
        int tac = 8;
        String binary = toBinary(10);
        System.out.println(binary);
        System.out.println(ac + tac);

    }


    public static void fund() {
        for (long i = Integer.MAX_VALUE - 10; i < Long.MAX_VALUE; i++) {
            System.out.println(i);
        }
    }


    public static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }

    public static int fuc3(int ac, int bc) {
        return ac + bc;
    }

    public int fuc4(int ac, int bc, String str) {
        switch (str) {
            case "a":
                return 1;
            case "b":
                return 2;
            default:
                return -1;
        }
    }

    public void fuc2() {
        try {
            int a = 10;
            if (a > 3) {
                return;
            }
            int b = 1000000;
            long c = 20L;
            long d = 0xfffffff;
            double e = 3.0d;
            float f = 3.0f;
        } catch (Exception e) {
            System.out.println("e");
        } finally {
            System.out.println("qbc");
        }

        List<String> list = new ArrayList();
        list.add("a");
        list.add("c");
        list.add("b");
        String result = list.stream().filter(t -> t.equals("a")).findFirst().orElse(null);

        synchronized ("ac"){
            System.out.println("ac");
        }
    }

}
