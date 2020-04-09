package com.zhd.study.suanfa;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-08 17:33
 */
public class UnixPathHandle {

    public static void main(String[] args) {
        String path = "/../x//../../../.x/../.j/../";
        Scanner sc = new Scanner(System.in);
        //获取输入的整数序列
        String line = sc.nextLine();

        String result = simplify(line);
        System.out.println(result);
    }

    public static String simplify(String path) {
        if (path == null || path.trim().equals("")) {
            return path;
        }
        String[] split = path.split("/");
        ArrayList<String> stack = new ArrayList<>(split.length);
        for (int i = 0; i < split.length; i++) {
            String p = split[i];
            if (p.equals("") || p.equals(".")) {
                continue;
            }
            if (p.equals("..")) {
                if (stack.size() > 0) {
                    stack.remove(stack.size() - 1);
                } else {
                    stack.add("..");
                }
            } else {
                if (stack.size() > 0 && stack.get(stack.size() - 1).equals("..")) {
                    stack.remove(stack.size() - 1);
                } else {
                    stack.add(p);
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (stack.size() > 0 && stack.get(0).equals("..")) {
            stack.remove(0);
        }
        for (int i = 0; i < stack.size(); i++) {
            stringBuffer.append("/");
            stringBuffer.append(stack.get(i));
        }
        if (stringBuffer.length() == 0) {
            stringBuffer.append("/");
        }
        return stringBuffer.toString();
    }

}
