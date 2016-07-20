package cn.boc.newsdemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.boc.newsdemo.R;
import cn.boc.newsdemo.TimeCount;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String MESSAGE_RECEIVER = "MESSAGE_RECEIVER";


    MyReceiver myReceiver;

    EditText codeEditText,phoneEditText;

    Button getCodeButton,loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        codeEditText = (EditText) findViewById(R.id.codeEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        getCodeButton = (Button) findViewById(R.id.getCodeButton);
        loginButton = (Button) findViewById(R.id.loginButton);


        getCodeButton.setOnClickListener(this);


        loginButton.setOnClickListener(this);


    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(myReceiver == null){
//            myReceiver = new MyReceiver();
//        }
//        IntentFilter intentFilter = new IntentFilter();
////        intentFilter.addAction(MESSAGE_RECEIVER);
//        this.registerReceiver(myReceiver,intentFilter);
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(myReceiver != null){
//            this.unregisterReceiver(myReceiver);
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:

                break;
            case R.id.getCodeButton:
                String phone = phoneEditText.getText().toString();
                if(phone.length() == 11){
                    TimeCount timeCount = new TimeCount(getCodeButton,60*1000,1000);
                    timeCount.start();
                }else{
                    Toast.makeText(this,"请输入合法的手机号",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MESSAGE_RECEIVER)){
                String smsMsg = intent.getStringExtra("sms_content");
                if(smsMsg != null){
                    codeEditText.setText(smsMsg);
                }
            }
        }
    }
}
