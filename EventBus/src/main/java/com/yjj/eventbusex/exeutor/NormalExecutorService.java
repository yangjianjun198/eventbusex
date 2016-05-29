package com.yjj.eventbusex.exeutor;

import android.support.annotation.NonNull;

import com.yjj.eventbusex.common.ELog;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class NormalExecutorService extends ConstraintExecutorService {

    private static NormalExecutorService instance;
    private static String TAG="NormalExecutorService";

    private static int QUEUE_SIZE=4;
    private static int MAX_CON_SIZE=5;
    private static String THREAD_NAME="normal";

    public NormalExecutorService(String name, int maxConcurrency, BlockingQueue<Runnable> workQueue) {
        super(name, maxConcurrency, workQueue);
    }

    public static NormalExecutorService of() {

        if (instance == null) {
            synchronized (NormalExecutorService.class) {
                if (instance == null) {
                    instance = new NormalExecutorService(THREAD_NAME, MAX_CON_SIZE,
                            new LinkedBlockingQueue<Runnable>(QUEUE_SIZE));
                }
            }
        }

        return instance;
    }


    @Override
    protected void runWorker() {

        ELog.v(TAG,"runWorker start");
        mTaskRunner.run();
    }

    @Override
    public void shutdown() {

        throw new UnsupportedOperationException("shutdown");
    }

    @NonNull
    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("shutdownNow");
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
