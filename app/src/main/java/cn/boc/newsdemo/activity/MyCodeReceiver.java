package cn.boc.newsdemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import static cn.boc.newsdemo.activity.LoginActivity.MESSAGE_ACTION;

/**
 * Created by wanglj on 16/7/20.
 */

public class MyCodeReceiver extends BroadcastReceiver{

    EditText editText;
    public MyCodeReceiver(EditText editText){
        this.editText = editText;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("MyCodeReceiver", "MyCodeReceiver");

        if(intent.getAction().equals(MESSAGE_ACTION)){
            String smsMsg = intent.getStringExtra("code");
            if(smsMsg != null){
                editText.setText(smsMsg);
            }
        }
    }
}
