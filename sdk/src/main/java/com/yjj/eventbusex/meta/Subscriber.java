package com.yjj.eventbusex.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jianjunyjj on 16/5/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscriber {
    ThreadType threadType()default ThreadType.NORMAL;
    PriorityType priorityType()default PriorityType.NORMAL;
}
