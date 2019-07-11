package com.ermile.salamquran.statice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ermile.salamquran.R;
import com.ermile.salamquran.online.enter.Enter;
import com.ermile.salamquran.saveData.SessionManager;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        String mobile = SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_mobile);
        if (mobile ==null){
            finish();
            startActivity(new Intent(this, Enter.class));
        }

    }
}
