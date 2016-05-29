package com.yjj.eventbusex.exeutor;

import android.support.annotation.NonNull;

import com.yjj.eventbusex.common.ELog;
import com.yjj.eventbusex.common.Performance;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class BackgroundExecutorService extends AbstractExecutorService {

    private static BackgroundExecutorService instance;
    private final ExecutorService mExecutor;

    private static String TAG="BackgroundExecutorService";


    public BackgroundExecutorService() {

        mExecutor =  Executors.newCachedThreadPool();
    }


    public static BackgroundExecutorService of() {

        if(instance==null){
            synchronized (BackgroundExecutorService.class){
                if(instance==null){
                    instance=new BackgroundExecutorService();
                }
            }
        }
        return instance;
    }

    @Override
    public void shutdown() {

        mExecutor.shutdown();
    }

    @NonNull
    @Override
    public List<Runnable> shutdownNow() {
        return mExecutor.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return mExecutor.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return mExecutor.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return mExecutor.awaitTermination(timeout,unit);
    }

    @Override
    public void execute(Runnable command) {
        mExecutor.execute(command);
    }
}
