package cn.boc.newsdemo.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import cn.boc.newsdemo.activity.LoginActivity;

/**
 * Created by wanglj on 16/7/20.
 */

public class SMSReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage[] smsMessages = null;

        Log.e("SMSReceiver","onreceiver");
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            Bundle bundle = intent.getExtras();

            if(bundle != null){
                Object[] pduses = (Object[]) bundle.get("pdus");
                if(pduses != null){
                    smsMessages = new SmsMessage[pduses.length];
                    for (int i = 0; i < smsMessages.length; i++) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pduses[i]);
                        smsMessages[i] = smsMessage;
                    }

                }



                String smsMsg = smsMessages[0].getMessageBody();

                if(smsMsg != null){
                    Intent smsIntent = new Intent();
                    intent.setAction(LoginActivity.MESSAGE_RECEIVER);

                    smsIntent.putExtra("sms_content",smsMsg);

                    context.sendBroadcast(smsIntent);
                }






            }

        }
    }
}
