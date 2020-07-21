package com.example.mytoolbar;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import static com.example.mytoolbar.Settings.APP_NUMBER;
import static com.example.mytoolbar.Settings.APP_SETTINGS;

public class MainActivity extends AppCompatActivity {
  private static MainActivity ins;


  public Toolbar toolbar;
 public Button arm_sms_btn, disarm_sms_btn;
  Settings settings = new Settings();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ins = this;

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar = findViewById(R.id.mytoolbar);
    setSupportActionBar(toolbar);

    arm_sms_btn = findViewById(R.id.arm_sms_btn);
    disarm_sms_btn = findViewById(R.id.disarm_sms_btn);

    arm_sms_btn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        settings.sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        String phone = settings.sharedPreferences.getString(APP_NUMBER, "");
        phone = "+" + phone;
          sendSMS(phone, "Тест смс");
      }
    });
  }

  public static MainActivity  getInstace(){
    return ins;
  }

  public void updateTheTextView(final String t) {
    MainActivity.this.runOnUiThread(new Runnable() {
      public void run() {
        Button textV1 = findViewById(R.id.arm_sms_btn);
        textV1.setText(t);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  public void openSettings(MenuItem item) {
    Intent intent = new Intent(".Settings");
    startActivity(intent);
  }


  private void sendSMS(String phoneNumber, String message)
  {
    String SENT="SMS_SENT";
    String DELIVERED="SMS_DELIVERED";

    PendingIntent sentPI= PendingIntent.getBroadcast(this,0,
      new Intent(SENT),0);

    PendingIntent deliveredPI= PendingIntent.getBroadcast(this,0,
      new Intent(DELIVERED),0);

//---когда SMS отправлено---
    registerReceiver(new BroadcastReceiver(){
      @Override
      public void onReceive(Context arg0, Intent intent){
        switch(getResultCode())
        {
          case Activity.RESULT_OK:
            arm_sms_btn.setBackgroundColor(0xffff9e10);
            arm_sms_btn.setText("Ожидаем ответа от датчика");
            break;
          case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
            arm_sms_btn.setBackgroundColor(0xffde3163);
            arm_sms_btn.setText("Ошибка отправки");

            break;
          case SmsManager.RESULT_ERROR_NO_SERVICE:
            arm_sms_btn.setBackgroundColor(0xffde3163);
            arm_sms_btn.setText("Ошибка отправки");
            break;
          case SmsManager.RESULT_ERROR_NULL_PDU:
            arm_sms_btn.setBackgroundColor(0xffde3163);
            arm_sms_btn.setText("Ошибка отправки");
            break;
          case SmsManager.RESULT_ERROR_RADIO_OFF:
            arm_sms_btn.setBackgroundColor(0xffde3163);
            arm_sms_btn.setText("Ошибка отправки");
            break;
        }
      }
    },new IntentFilter(SENT));

//---когда SMS доставлено---
    registerReceiver(new BroadcastReceiver(){
      @Override
      public void onReceive(Context arg0, Intent arg1){
        switch(getResultCode())
        {
          case Activity.RESULT_OK:
            arm_sms_btn.setBackgroundColor(0xffffcc99);
            arm_sms_btn.setText("Ожидаем постановки..");

            break;
          case Activity.RESULT_CANCELED:
            arm_sms_btn.setBackgroundColor(808080);
            arm_sms_btn.setText("Сообщение не доставлено.");
            break;
        }
      }
    },new IntentFilter(DELIVERED));

    SmsManager sms= SmsManager.getDefault();
    sms.sendTextMessage(phoneNumber,null, message, sentPI, deliveredPI);
  }

}

