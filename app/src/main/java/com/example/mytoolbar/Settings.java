package com.example.mytoolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {
  public static final String APP_SETTINGS = "settings";
  public static final String APP_NUMBER = "phoneForModule";
  public SharedPreferences sharedPreferences;
  Long phone ;
  public EditText phoneField;
  Button save_phone_btn, readBtnPhoneNumber;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    save_phone_btn = findViewById(R.id.save_phone_btn);
    phoneField   = findViewById(R.id.phone_field_setting);
    readBtnPhoneNumber = findViewById(R.id.readBtnPhoneNumber);
    save_phone_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String temp = phoneField.getText().toString();

          phone = Long.parseLong (temp);
          sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putLong(APP_NUMBER, phone);
          editor.apply();
      }
    });
    readBtnPhoneNumber.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        phone = sharedPreferences.getLong(APP_NUMBER, 0);
        String phoneNumber = Long.toString(phone);
          phoneField.setText( phoneNumber);

      }
    });

}
 }
