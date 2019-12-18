package com.ermile.salamquran.android.salamquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ermile.salamquran.android.QuranDataActivity;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.TempLoginUtil;

public class Splash extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    new TempLoginUtil(getApplication());
    nextActivity();
  }

  private void nextActivity(){
    finish();
    startActivity(new Intent(getApplication(), QuranDataActivity.class));
  }
}
