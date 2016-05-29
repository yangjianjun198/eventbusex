package com.yjj.eventbusex.exeutor;

import java.util.concurrent.Executor;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public interface ExecutorSupplier {

    public Executor forMain();
    public Executor forBackground();
    public Executor forNormal();
}
