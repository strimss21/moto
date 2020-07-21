package com.example.mytoolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Button;

public class SmsReceiver extends BroadcastReceiver {
  boolean b = false;

  @Override
  public void onReceive(Context context, Intent intent) {

    //Это наччало!!!!!!!!!!!!!!!!!!!



    //---получить входящее SMS сообщение---
    Bundle bundle = intent.getExtras();
    SmsMessage[] msgs = null;
    String str = "";
    if (bundle != null) {
      //---извлечь полученное SMS ---
      Object[] pdus = (Object[]) bundle.get("pdus");
      msgs = new SmsMessage[pdus.length];
      for (int i = 0; i < msgs.length; i++) {
        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        str += msgs[i].getMessageBody();
      }
      System.out.println("Это  будет str: " + str);

     if(str.equals("Тест смс")){
       try {
         MainActivity  .getInstace().updateTheTextView("Поставлено!!");
       } catch (Exception e) {

       }
     }
     else {
       System.out.println(b);
     }
    }

  }

}

