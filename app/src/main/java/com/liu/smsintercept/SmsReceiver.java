package com.liu.smsintercept;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by liu on 2015/12/30 0030.
 */
public class SmsReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        //获取短信数组
        Object[] objects= (Object[]) bundle.get("pdus");
        for(Object obj:objects){
            //获取短信
            SmsMessage sms=SmsMessage.createFromPdu((byte[]) obj);
            //获取对方号码
            String address=sms.getOriginatingAddress();
            //获取短信内容
            String body=sms.getMessageBody();
            Log.d("debug_sms",address+"   "+body);
            //判断数据库中是否存在本号码
            if(DBUtil.findBlacklistByAddress(context,address)!=null){
                //拦截广播
                abortBroadcast();
                Toast.makeText(context,"已拦截"+address+"的短信",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
