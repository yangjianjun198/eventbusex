package com.yjj.eventbusex.exeutor;

import android.support.annotation.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by jianjunyjj on 16/5/27.
 */
public class ScheduledFutureImpl<T> implements RunnableFuture<T> ,ScheduledFuture<T>{

    private FutureTask<T> mListenableFuture;
    public ScheduledFutureImpl( Callable<T> callable) {

        mListenableFuture = new FutureTask<T>(callable);
    }

    public ScheduledFutureImpl( Runnable runnable, @Nullable T result) {

        mListenableFuture = new FutureTask<T>(runnable, result);
    }
    @Override
    public void run() {
        mListenableFuture.run();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return mListenableFuture.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return mListenableFuture.isCancelled();
    }

    @Override
    public boolean isDone() {
        return mListenableFuture.isDone();
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return mListenableFuture.get();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return mListenableFuture.get(timeout, unit);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed another) {
        return 0;
    }
}
