package com.zhd.study.classLoad;

import io.netty.util.concurrent.SingleThreadEventExecutor;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-14 14:46
 */
public class ServiceLoadTest {
    public static void main(String[] args) {
        ServiceLoader<Customer> s = ServiceLoader.load(Customer.class);
        Iterator<Customer> iHelloIterator = s.iterator();
        while (iHelloIterator.hasNext()) {
            Customer customer = iHelloIterator.next();
            customer.isVIP();
        }


    }
}
