package com.yjj.eventbusex.datasource;

import com.yjj.eventbusex.meta.IEventEntry;

import java.util.Map;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public interface IDataSource {

    public void addSubscriber(Object subscriber);
    public void removeSubscriber(Object subscriber);
    public Map<Class, IEventEntry> getSubscriberMethods();
}
