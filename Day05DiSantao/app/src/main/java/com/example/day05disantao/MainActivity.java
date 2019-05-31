package com.example.day05disantao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


//惠微乐
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int c = 3;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mTvDaojishi.setText(c--+"");
            if (msg.what == 11) {
                if (c == 0) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    private ImageView mIvTiao;
    /**
     * 倒计时
     */
    private TextView mTvDaojishi;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mIvTiao = (ImageView) findViewById(R.id.iv_tiao);
        mTvDaojishi = (TextView) findViewById(R.id.tv_daojishi);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(11);
            }
        }, 100, 1000);
        mIvTiao.setOnClickListener(this);
        mTvDaojishi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_tiao:
                break;
            case R.id.tv_daojishi:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
