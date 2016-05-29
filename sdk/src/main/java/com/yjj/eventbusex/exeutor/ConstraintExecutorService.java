package com.yjj.eventbusex.exeutor;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.yjj.eventbusex.common.ELog;
import com.yjj.eventbusex.common.PreConditions;


/**
 * Created by jianjunyjj on 16/5/28.
 */
public abstract class ConstraintExecutorService extends AbstractExecutorService {

    private static final String TAG = "ConstraintExecutorService";

    private final String mName;

    private volatile int mMaxConcurrency;
    private final BlockingQueue<Runnable> mWorkQueue;

    protected final Worker mTaskRunner;
    private final AtomicInteger mPendingWorkers;
    private final AtomicInteger mMaxQueueSize;


    public ConstraintExecutorService(
            String name,
            int maxConcurrency,
            BlockingQueue<Runnable> workQueue) {

        PreConditions.checkArgument(maxConcurrency > 0,"max concurrency must be > 0");
        mName = name;
        mMaxConcurrency = maxConcurrency;
        mWorkQueue = workQueue;
        mTaskRunner = new Worker();
        mPendingWorkers = new AtomicInteger(0);
        mMaxQueueSize = new AtomicInteger(0);
    }




    protected abstract void runWorker();

    public boolean isIdle() {
        return mWorkQueue.isEmpty() && (mPendingWorkers.get() == 0);
    }


    @Override
    public void execute(Runnable runnable) {

        PreConditions.checkNotNull(runnable,"execute runnable is null");

        if (!mWorkQueue.offer(runnable)) {
            throw new RejectedExecutionException(
                    mName + " queue is full, size=" + mWorkQueue.size());
        }

        final int queueSize = mWorkQueue.size();
        final int maxSize = mMaxQueueSize.get();
        if ((queueSize > maxSize) && mMaxQueueSize.compareAndSet(maxSize, queueSize)) {
            ELog.v(TAG, "%s: max pending work in queue = %d",mName, queueSize);
        }

        startWorkerIfNeeded();
    }


    private void startWorkerIfNeeded() {

        int currentCount = mPendingWorkers.get();
        while (currentCount < mMaxConcurrency) {
            int updatedCount = currentCount + 1;
            if (mPendingWorkers.compareAndSet(currentCount, updatedCount)) {
                ELog.v(TAG, "%s: starting worker %d of %d", mName, updatedCount, mMaxConcurrency);
                runWorker();
                break;
            }

            ELog.v(TAG, "%s: race in startWorkerIfNeeded; retrying", mName);
            currentCount = mPendingWorkers.get();
        }
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }


    protected class Worker implements Runnable {

        @Override
        public void run() {
            try {
                Runnable runnable = mWorkQueue.poll();
                if (runnable != null) {
                    runnable.run();
                } else {

                    ELog.v(TAG, "%s: Worker has nothing to run", mName);
                }
            } finally {
                int workers = mPendingWorkers.decrementAndGet();
                if (!mWorkQueue.isEmpty()) {
                    startWorkerIfNeeded();
                } else {
                    ELog.v(TAG, "%s: worker finished; %d workers left", mName, workers);
                }
            }
        }
    }
}