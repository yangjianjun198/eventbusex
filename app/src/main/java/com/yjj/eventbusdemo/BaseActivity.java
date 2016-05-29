package com.yjj.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yjj.eventbusex.meta.Subscriber;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Subscriber
    public void onEventM(com.yjj.eventbusdemo.MainEvent mainEvent){
        Log.d("yjj","onEventM");
    }
}
