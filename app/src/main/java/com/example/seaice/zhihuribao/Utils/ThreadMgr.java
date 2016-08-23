package com.example.seaice.zhihuribao.Utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by seaice on 2016/8/12.
 */
public class ThreadMgr {
    private static ThreadPool threadPool;

    public static ThreadPool getThreadPool(){
        if(threadPool == null){
            synchronized (ThreadMgr.class){
                if(threadPool == null){
                    int corePoolSize = 5;
                    int maxPoolSize = 10;
                    long keepAliveTime = 0L;
                    threadPool = new ThreadPool(corePoolSize,maxPoolSize,keepAliveTime);
                }
            }
        }
        return threadPool;
    }

    public static class ThreadPool{
        public static ThreadPoolExecutor executor = null;

        private int corePoolSize;
        private int maxPoolSize;
        private long keepAliveTime = 0;

        public ThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTime){
            this.corePoolSize = corePoolSize;
            this.maxPoolSize = maxPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public void execute(Runnable runnable){
            if(runnable == null){
                return;
            }

            if (executor == null) {
                /**
                 * 1.corePoolSize:里面可以开辟多少个线程
                 * 2.如果排队满了，额外的开的线程数
                 * 3.如果这个线程池没有要执行的任务，存活多久
                 * 4.时间单位
                 * 5.如果，线程池里管理的线程都已经用了，把剩下的任务都先临时存到这个对象中排队
                 */
                executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<Runnable>(), Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
            }
            executor.execute(runnable);
        }

        public void cancel(Runnable runnable){
            if(executor!=null && !executor.isShutdown() && !executor.isTerminated()){
                executor.remove(runnable);
            }
        }
    }
}
