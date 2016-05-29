package com.yjj.eventbusex.meta;

import java.lang.reflect.Method;

/**
 * Created by jianjunyjj on 16/5/27.
 */
public interface IEventData {

    public Object getObject();
    public Method getMethod();
    public ThreadType getThreadType();

    public PriorityType getPriority();



}
