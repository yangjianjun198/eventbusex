package com.yjj.eventbusex.meta;

import com.yjj.eventbusex.common.PreConditions;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by jianjunyjj on 16/5/28.
 */
public class EventEntry implements IEventEntry {


    private List<IEventData> mEventDatas = new CopyOnWriteArrayList<>();
    private Class eventType;


    @Override
    public List<IEventData> getEventDatas() {
        return mEventDatas;
    }

    public EventEntry(Class eventType) {
        this.eventType = eventType;
    }


    @Override
    public void add(IEventData eventData) {

        innerAdd(eventData);

    }


    private void innerAdd(IEventData eventData) {
        if (!mEventDatas.contains(eventData)) {
            int insertInto = -1;
            int size = mEventDatas.size();
            for (int i = 0; i < size; i++) {
                if (eventData.getPriority().value >= mEventDatas.get(i).getPriority().value) {
                    insertInto = i;
                    break;
                }
            }
            if (insertInto >= 0) {//如果有比他优先级低的
                mEventDatas.add(insertInto, eventData);
            } else {//他优先级最低
                mEventDatas.add(eventData);
            }
        }
    }


    @Override
    public IEventData remove(IEventData eventData) {
        eventData = PreConditions.checkNotNull(eventData);
        for (IEventData e : mEventDatas) {
            if (e.getObject() == eventData.getObject()) {
                mEventDatas.remove(e);
            }
        }

        return eventData;
    }


    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (o instanceof EventEntry) {
            EventEntry other = (EventEntry) o;
            if ((other.eventType == this.eventType)) {
                return true;
            }
        }
        return false;
    }
}
