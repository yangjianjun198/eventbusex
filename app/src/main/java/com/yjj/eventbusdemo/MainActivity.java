package com.yjj.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yjj.eventbusdemo.R;
import com.yjj.eventbusex.EventBus;
import com.yjj.eventbusex.meta.Subscriber;
import com.yjj.eventbusex.meta.ThreadType;





public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long startTime;
    private long startTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.my).setOnClickListener(this);
        findViewById(R.id.green).setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getInstance().register(this);


    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    public void onEventX10(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX11(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX12(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX13(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX14(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX15(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX16(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX1111(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX211(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX31(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX41(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX511(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX611(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX71(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX81(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX91(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX101(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX111(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX121(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX131(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX141(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX151(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    public void onEventX61(MainEvent event) {

        Log.d("yjj", "onEvent " + event.type + "thread" + Thread.currentThread().getName());
    }

    @Subscriber(threadType = ThreadType.MAIN)
    public void onEvent(MainEvent event) {
        Toast.makeText(this,"the message is "+event.type,Toast.LENGTH_SHORT).show();
    }


    public void onEvexntX(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventXx(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }

    public void onEvent3X(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventX2(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventX1(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEvent31X(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventXX(MainEvent event) {
        Log.d("yjj", "3thread +" + Thread.currentThread().getName());
    }


    public void onEvexssntX(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEvenstXx(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }

    public void onEvenst3X(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventsX2(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventXs1(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEvent31sX(MainEvent event) {
        Log.d("yjj", "2thread +" + Thread.currentThread().getName());
    }


    public void onEventXsX(MainEvent event) {
        Log.d("yjj", "3thread +" + Thread.currentThread().getName());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my) {
            startTime = System.currentTimeMillis();
            EventBus.getInstance().postEvent(new MainEvent());

        }else if(v.getId()==R.id.green){
            Intent tmpIntent=new Intent(this,SecondActivity.class);
            startActivity(tmpIntent);
        }
    }
}
