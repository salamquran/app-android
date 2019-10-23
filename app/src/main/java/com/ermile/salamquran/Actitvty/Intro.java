package com.ermile.salamquran.Actitvty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.log;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        log.d(this.getClass().getName(),"Open Oncreate",null);
        SaveManager.get(getApplicationContext()).change_intriOpen(true);
        finish();
        startActivity(new Intent(getApplicationContext(),Main.class));

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
