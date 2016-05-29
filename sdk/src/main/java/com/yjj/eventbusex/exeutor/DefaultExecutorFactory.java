package com.yjj.eventbusex.exeutor;

import com.yjj.eventbusex.meta.ThreadType;

import java.util.concurrent.Executor;


/**
 * Created by jianjunyjj on 16/5/28.
 */
public class DefaultExecutorFactory implements ExecutorFactory {

    private ExecutorSupplier mSupplier;

    private Executor mMainExecutor;
    private Executor mBackgroundExecutor;
    private Executor mNormalExecutor;
    public DefaultExecutorFactory() {
        mSupplier = new DefaultExecutorSupplier();
        mMainExecutor=mSupplier.forMain();
        mBackgroundExecutor=mSupplier.forBackground();
        mNormalExecutor=mSupplier.forNormal();
    }

    @Override
    public Executor get(ThreadType threadModel) {

        if (threadModel == ThreadType.BACKGROUND) {
            return mBackgroundExecutor;
        } else if (threadModel == ThreadType.MAIN) {
            return mMainExecutor;
        }else {
           return mNormalExecutor;
        }
    }
}
