package com.yjj.eventbusex.exeutor;

import com.yjj.eventbusex.common.ELog;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class BackgroundExecutorService extends ConstraintExecutorService {

    private static BackgroundExecutorService instance;
    private final Executor mExecutor;
    private static int QUEUE_SIZE=4;
    private static int MAX_CON_SIZE=5;
    private static String THREAD_NAME="background";

    private static String TAG="BackgroundExecutorService";


    public BackgroundExecutorService(String name, int maxConcurrency,  BlockingQueue<Runnable> workQueue) {
        super(name, maxConcurrency, workQueue);
        mExecutor =  Executors.newCachedThreadPool();
    }

    @Override
    protected void runWorker() {

        ELog.v(TAG,"runWorker start");
        mExecutor.execute(mTaskRunner);

    }

    public static BackgroundExecutorService of() {

        if(instance==null){
            synchronized (BackgroundExecutorService.class){
                if(instance==null){
                    instance=new BackgroundExecutorService(
                            THREAD_NAME,
                            MAX_CON_SIZE,
                            new LinkedBlockingQueue<Runnable>(QUEUE_SIZE));
                }
            }
        }
        return instance;
    }

}
