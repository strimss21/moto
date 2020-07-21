package com.example.mytoolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.pinball83.maskededittext.MaskedEditText;

public class Settings extends AppCompatActivity {



  public static final String APP_SETTINGS = "settings";
  public static final String APP_NUMBER = "phoneForModule";
  public SharedPreferences sharedPreferences;
  String url;
  String phone ;
  public static final String urlForModule = "url";
  public MaskedEditText phoneField;
  public EditText url_field;
  Button save_phone_btn, readBtnPhoneNumber, save_url_btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    save_phone_btn = findViewById(R.id.save_phone_btn);
    save_url_btn = findViewById(R.id.save_url_btn);
    phoneField   = findViewById(R.id.phone_field_setting);
    url_field = findViewById(R.id.url_field);

    readBtnPhoneNumber = findViewById(R.id.readBtnPhoneNumber);

    save_phone_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        phone = phoneField.getUnmaskedText();
         sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putString(APP_NUMBER,  phone);
          editor.apply();
      }
    });
    readBtnPhoneNumber.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        phone = sharedPreferences.getString(APP_NUMBER, "");
        String phoneNumber = phone;
          phoneField.setMaskedText( phoneNumber);
        url = sharedPreferences.getString(urlForModule, "");
        url_field.setText( url);
      }
    });

    save_url_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         url = url_field.getText().toString();
        sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(urlForModule, url);
        editor.apply();
      }
    });



}
 }
