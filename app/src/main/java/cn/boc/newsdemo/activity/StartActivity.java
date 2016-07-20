package cn.boc.newsdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import cn.boc.newsdemo.R;

public class StartActivity extends AppCompatActivity {




    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(StartActivity.this,LoginActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                Message message =  new Message();
//                message.what = 2;
//                message.obj = "发送一条消息";
//                handler.sendMessage(message);

                handler.sendEmptyMessage(2);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(StartActivity.this,LoginActivity.class));
//                    }
//                });

            }
        }).start();





    }
}
