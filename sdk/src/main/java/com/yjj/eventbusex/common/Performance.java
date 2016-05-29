package com.yjj.eventbusex.common;

/**
 * Created by jianjunyjj on 16/5/29.
 */
public class Performance {


    private long mStartTime;
    private long mEndTime;

    public Performance() {
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
    }

    public void end(String message) {
        mEndTime = System.currentTimeMillis();
        print(message);
    }

    private void print(String message){
        ELog.v("performance",message+" cost time "+(mEndTime-mStartTime));
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
