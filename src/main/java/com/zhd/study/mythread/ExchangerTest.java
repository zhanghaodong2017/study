package com.zhd.study.mythread;

import java.util.concurrent.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 22:20
 */
public class ExchangerTest {

    public static ExecutorService service = new ThreadPoolExecutor(10, 20,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());


    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        for (int i = 0; i < 4; i++) {
            int temp = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        String acn = "xiaofeizhe - " + temp;
                        String data = exchanger.exchange(acn);
                        TimeUnit.SECONDS.sleep(2);
                        // 现在取得了生产者的数据
                        System.out.println("【" + Thread.currentThread().getName() + "】" + data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

       /* for (int i = 0; i < 10; i++) {
            int temp = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    String data = "MLDN - " + temp;
                    try {
                        // 让生产者放慢节奏
                        TimeUnit.SECONDS.sleep(2);
                        // 将生产的数据保存在交换空间
                        String xiaofei = exchanger.exchange(data);
                        System.out.println(xiaofei + "【" + Thread.currentThread().getName() + "】生产了数据：" + data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
*/

    }
}
