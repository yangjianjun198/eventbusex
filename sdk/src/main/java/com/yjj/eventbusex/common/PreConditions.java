package com.yjj.eventbusex.common;

/**
 * Created by jianjunyjj on 16/5/27.
 */
public class PreConditions {

    public static <T> T checkNotNull(T obj){

        if(obj==null){
            throw new NullPointerException("is null");
        }
        return obj;
    }

    public static <T> T checkNotNull(T obj,String message){

        if(obj==null){
            throw new NullPointerException(message);
        }
        return obj;
    }

    public static void checkArgument(boolean expression,String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
