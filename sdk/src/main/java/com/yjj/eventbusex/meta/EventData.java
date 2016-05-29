package com.yjj.eventbusex.meta;

import java.lang.reflect.Method;

/**
 * Created by jianjunyjj on 16/5/27.
 */
public class EventData implements IEventData {

    Object mObject;
    Method mMethod;

    ThreadType mThreadType;

    PriorityType mPriorityType;
    Class mEventType;

    public EventData(Object subcriber, Method method, ThreadType model, PriorityType priorityModel, Class eventType) {
        this.mMethod = method;
        this.mObject = subcriber;
        this.mThreadType = model;
        this.mPriorityType = priorityModel;
        this.mEventType = eventType;
    }

    public EventData(Object object) {
        mObject = object;
    }

    @Override
    public Object getObject() {
        return mObject;
    }

    @Override
    public Method getMethod() {
        return mMethod;
    }

    @Override
    public ThreadType getThreadType() {
        return mThreadType;
    }

    @Override
    public PriorityType getPriority() {
        return mPriorityType;
    }

    public void setThreadType(ThreadType threadType) {
        mThreadType = threadType;
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (o instanceof EventData) {
            EventData other = (EventData) o;
            if (other.mMethod == this.mMethod && other.mObject == this.mObject
                    && other.mThreadType == this.mThreadType
                    && other.mPriorityType == this.mPriorityType
                    && this.mEventType == other.mEventType) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {

        StringBuffer tmpStringBuffer = new StringBuffer(20);
        tmpStringBuffer.append("[ method:");
        tmpStringBuffer.append(mMethod.getName());
        tmpStringBuffer.append(",subcriber:");
        tmpStringBuffer.append(mObject.getClass().getSimpleName());
        tmpStringBuffer.append("]");
        return tmpStringBuffer.toString();
    }


}
