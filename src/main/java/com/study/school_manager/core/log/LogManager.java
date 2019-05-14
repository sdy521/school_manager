package com.study.school_manager.core.log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author sdy
 * @date 2019/5/9 17:09
 */
public class LogManager {

    //固定线程池大小5，当有其他任务到来时进入队列等待
    private static final Executor executor = Executors.newFixedThreadPool(5);

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }
}
