package com.yjj.eventbusex.exeutor;

import java.util.concurrent.Executor;


/**
 * Created by jianjunyjj on 16/5/28.
 */
public class DefaultExecutorSupplier implements ExecutorSupplier {
    @Override
    public Executor forMain() {
        return MainExecutorService.of();
    }

    @Override
    public Executor forBackground() {
        return BackgroundExecutorService.of();
    }

    @Override
    public Executor forNormal() {
        return NormalExecutorService.of();
    }
}
