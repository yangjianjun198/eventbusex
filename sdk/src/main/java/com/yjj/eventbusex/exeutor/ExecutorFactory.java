package com.yjj.eventbusex.exeutor;

import com.yjj.eventbusex.meta.ThreadType;

import java.util.concurrent.Executor;


/**
 * Created by jianjunyjj on 16/5/28.
 */
public interface ExecutorFactory {

    Executor get(ThreadType threadModel);
}
