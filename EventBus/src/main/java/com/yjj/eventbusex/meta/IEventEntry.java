package com.yjj.eventbusex.meta;

import java.util.List;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public interface IEventEntry {

    List<IEventData> getEventDatas();

    void add(IEventData eventData);
    IEventData remove(IEventData eventData);



}
