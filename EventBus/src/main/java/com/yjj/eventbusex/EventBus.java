package com.yjj.eventbusex;


import com.yjj.eventbusex.common.Performance;
import com.yjj.eventbusex.datasource.DataSource;
import com.yjj.eventbusex.datasource.IDataSource;
import com.yjj.eventbusex.exeutor.IJopScheduler;
import com.yjj.eventbusex.exeutor.JopScheduler;


/**
 * Created by jianjunyjj on 16/5/27.
 */
public final class EventBus {

    public static EventBus instance;
    private IDataSource mDataSource;

    private IJopScheduler mJopScheduler;


    public static EventBus getInstance() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    private EventBus() {

        mDataSource = new DataSource();
        mJopScheduler = new JopScheduler(mDataSource.getSubscriberMethods());
    }

    public void register(Object subscriber) {

        if (subscriber == null) {
            throw new EventBusException("subscriber is null");
        }
        Performance tmpPerformance = new Performance();
        tmpPerformance.start();
        mDataSource.addSubscriber(subscriber);
        tmpPerformance.end("add subscriber");


    }


    public void postEvent(final Object event) {

        mJopScheduler.execute(event);

    }

    public void unRegister(Object subcriber) {
        mDataSource.removeSubscriber(subcriber);

    }

}
