package com.yjj.eventbusex.exeutor;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by jianjunyjj on 16/5/27.
 */
public interface HandlerExeutorService extends ScheduledExecutorService {

    public void quit();
    public boolean isHandlerThread();
}
