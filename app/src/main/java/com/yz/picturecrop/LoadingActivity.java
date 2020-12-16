package com.yz.picturecrop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    private ProgressBar mProg;
    private int i = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(i == 101){
                startActivity(new Intent(LoadingActivity.this,MainActivity.class));
                finish();
            } else {
                mProg.setProgress(i);
                i ++;
                handler.sendEmptyMessageDelayed(0,20);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initView();
        getSupportActionBar().hide();
        handler.sendEmptyMessageDelayed(0,20);
    }

    private void initView() {
        mProg = findViewById(R.id.prog);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
