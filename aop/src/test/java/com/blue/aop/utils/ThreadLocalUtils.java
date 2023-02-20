package com.blue.aop.utils;

/**
 * @projectName: framework
 * @package: com.blue.aop.utils
 * @className: ThreadLocalUtils
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/18 15:52
 */
public class ThreadLocalUtils {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static long get(){
        if(threadLocal == null){
            throw new IllegalStateException("ThreadLocal");
        }
        return threadLocal.get();
    }

    public static void set(long startTime){
        threadLocal.set(startTime);
    }

    public static void remove(){
        threadLocal.remove();
    }

}