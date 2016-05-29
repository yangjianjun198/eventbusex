package com.yjj.eventbusex.exeutor;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class MainExecutorService extends HandlerExeutorServiceImpl {

    private static HandlerExeutorServiceImpl instance = null;


    private MainExecutorService() {
        super(new Handler(Looper.getMainLooper()));
    }

    public static HandlerExeutorServiceImpl of() {

        if(instance==null){
            synchronized (MainExecutorService.class){
                if(instance==null) {
                    instance = new MainExecutorService();
                }
            }
        }
        return instance;
    }

    @Override
    public void execute(Runnable command) {
        if (isHandlerThread()) {
            command.run();
        } else {
            super.execute(command);
        }
    }
}
