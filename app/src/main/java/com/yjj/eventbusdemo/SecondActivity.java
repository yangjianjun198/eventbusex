package com.yjj.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yjj.eventbusdemo.R;
import com.yjj.eventbusex.EventBus;
import com.yjj.eventbusex.meta.Subscriber;
import com.yjj.eventbusex.meta.ThreadType;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getInstance().register(this);

        for(int i=0;i<10;i++)
        EventBus.getInstance().register(new SecondActivity());
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getInstance().postEvent(new MainEvent());
                EventBus.getInstance().postEvent(new Event());
            }
        });
    }

    @Subscriber

    public void onEvent(MainEvent event){

        Log.d("yjj","onEvent");
    }

    @Subscriber(threadType = ThreadType.BACKGROUND)

    public void onEventX(Event event){

        Log.d("yjj","onEventX thread "+Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unRegister(this);
    }
}
