package com.example.mytoolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
public Toolbar toolbar;
public Menu menu;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar =findViewById(R.id.mytoolbar);
    setSupportActionBar(toolbar);
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  public void openSettings(MenuItem item) {
    Intent intent = new Intent(".Settings");
    startActivity(intent);
  }
}
