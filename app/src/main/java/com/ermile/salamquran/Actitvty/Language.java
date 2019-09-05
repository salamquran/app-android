package com.ermile.salamquran.Actitvty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;

public class Language extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Log.d(tag.activity, "onCreate: Language.class");
    }
}
