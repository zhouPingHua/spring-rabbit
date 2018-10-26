package com.dingcheng.confirms.consumer.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zph  on 2018/8/8
 */
public class MessageRecvExecutor {

    private static ThreadPoolExecutor threadPoolExecutor;

    public static void submit(Runnable task){
        if(threadPoolExecutor == null){
            synchronized (MessageRecvExecutor.class) {
                if(threadPoolExecutor == null){
                    threadPoolExecutor = new ThreadPoolExecutor(100, 100, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
                }
            }
        }
        threadPoolExecutor.submit(task);
    }
}
