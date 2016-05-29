package com.yjj.eventbusex.datasource;

import com.yjj.eventbusex.EventBusException;
import com.yjj.eventbusex.common.Constants;
import com.yjj.eventbusex.common.ELog;
import com.yjj.eventbusex.common.PreConditions;
import com.yjj.eventbusex.meta.EventData;
import com.yjj.eventbusex.meta.EventEntry;
import com.yjj.eventbusex.meta.IEventData;
import com.yjj.eventbusex.meta.IEventEntry;
import com.yjj.eventbusex.meta.Subscriber;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class DataSource implements IDataSource {


    private final Map<Class, IEventEntry> mMethods = new ConcurrentHashMap<Class, IEventEntry>();

    private final Map<Object, List<Class>> mSubscribers = new ConcurrentHashMap<Object, List<Class>>();

    private String TAG = "DataSource";

    public DataSource() {

    }

    @Override
    public void addSubscriber(Object subcriber) {
        subcriber = PreConditions.checkNotNull(subcriber);
        if (!mSubscribers.containsKey(subcriber)) {
            recursiveAddMethods(subcriber, subcriber.getClass());

        }
    }

    private void updateSubcsribersInfo(Object subcriber, Class cls) {

        List<Class> eventType = mSubscribers.get(subcriber);
        if (eventType == null) {
            eventType = new CopyOnWriteArrayList<>();
            eventType.add(cls);
            mSubscribers.put(subcriber, eventType);
        } else {
            if (!eventType.contains(cls)) {
                eventType.add(cls);
            }
        }
    }

    private void recursiveAddMethods(Object subscriber, Class subClass) {

        if (subClass == null) {
            return;
        }
        String clsName = subClass.getCanonicalName();
        if(clsName==null||clsName.isEmpty()){
            return;
        }
        ELog.v(TAG, "class name=" + clsName);
        if (clsName.startsWith("android") || clsName.startsWith("java")) {
            return;
        }
        Method[] methods = subClass.getDeclaredMethods();
        if (methods == null) {
            return;
        }
        int ii;
        int len = methods.length;
        Method mthd;
        for (ii = 0; ii < len; ii++) {
            mthd = methods[ii];
            if ((mthd.getModifiers() & Constants.MODIFIER_INAVAIL) != 0) {
                continue;
            }

            if (mthd.isAnnotationPresent(Subscriber.class)) {
                Class[] parcls = mthd.getParameterTypes();
                if (parcls == null || parcls.length > 1) {
                    throw new EventBusException("method params must only one");
                }

                updateSubcsribersInfo(subscriber, parcls[0]);

                Subscriber tmpSubscriber = mthd.getAnnotation(Subscriber.class);
                IEventData eventData = new EventData(subscriber, mthd, tmpSubscriber.threadType(), tmpSubscriber.priorityType(), parcls[0]);

                ELog.v(TAG, "add eventData:" + eventData.toString());

                if (mMethods.containsKey(parcls[0])) {
                    IEventEntry entry = mMethods.get(parcls[0]);
                    entry.add(eventData);
                } else {
                    IEventEntry tmpEntry = new EventEntry(parcls[0]);
                    tmpEntry.add(eventData);
                    mMethods.put(parcls[0], tmpEntry);
                }

            }
        }
        recursiveAddMethods(subscriber, subClass.getSuperclass());
    }


    @Override
    public void removeSubscriber(Object subcriber) {
        if (subcriber == null) {
            return;
        }
        List<Class> eventTypes = mSubscribers.get(subcriber);
        if (eventTypes != null) {
            int len = eventTypes.size();
            for (int i = 0; i < len; i++) {
                Class tmpClass = eventTypes.get(i);
                IEventEntry tmpEntry = mMethods.get(tmpClass);
                IEventData tmpIEventData = new EventData(subcriber);
                tmpEntry.remove(tmpIEventData);
            }
            mSubscribers.remove(subcriber);
        }

    }

    @Override
    public Map<Class, IEventEntry> getSubscriberMethods() {
        return mMethods;
    }

}
