package com.yjj.eventbusex.exeutor;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.yjj.eventbusex.common.PreConditions;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



/**
 * Created by jianjunyjj on 16/5/27.
 */
public class HandlerExeutorServiceImpl extends AbstractExecutorService implements HandlerExeutorService {

    private Handler mHandler;

    protected HandlerExeutorServiceImpl(Handler handler) {
        mHandler = handler;
    }
    

    @Override
    public void quit() {

        mHandler.getLooper().quit();
    }

    @Override
    public boolean isHandlerThread() {
        return mHandler.getLooper().getThread() == Thread.currentThread();
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

    @Override
    public void execute(Runnable command) {

        mHandler.post(command);
    }

    @Override
    public Future<?> submit(Runnable task) {

        return submit(task, null);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {

        task = PreConditions.checkNotNull(task);
        RunnableFuture<T> tmpFuture = newTaskFor(task, result);
        execute(tmpFuture);
        return tmpFuture;
    }

    @Override
    protected <T> ScheduledFutureImpl<T> newTaskFor(Callable<T> callable) {
        callable = PreConditions.checkNotNull(callable);
        return new ScheduledFutureImpl<T>(callable);
    }

    @Override
    protected <T> ScheduledFutureImpl<T> newTaskFor(Runnable runnable, T value) {
        runnable= PreConditions.checkNotNull(runnable);
        return new ScheduledFutureImpl<T>(runnable, value);
    }


    @NonNull
    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {

        command= PreConditions.checkNotNull(command);
        ScheduledFutureImpl tmpScheduledFuture = newTaskFor(command, null);
        mHandler.postDelayed(tmpScheduledFuture, delay);

        return tmpScheduledFuture;
    }

    @NonNull
    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {

        callable= PreConditions.checkNotNull(callable);
        ScheduledFutureImpl<V> tmpVScheduledFuture = newTaskFor(callable);
        mHandler.postDelayed(tmpVScheduledFuture, delay);
        return tmpVScheduledFuture;
    }

    @NonNull
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        throw new UnsupportedOperationException("scheduleAtFixedRate");
    }

    @NonNull
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        throw new UnsupportedOperationException("scheduleWithFixedDelay");
    }
}

