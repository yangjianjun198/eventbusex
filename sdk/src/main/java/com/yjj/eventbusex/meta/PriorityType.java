package com.yjj.eventbusex.meta;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public enum PriorityType {

    HIGH(1),
    NORMAL(0),
    LOW(-1);

    int value;

    PriorityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
