package com.ermile.salamquran.Actitvty;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.SaveManager;
import com.ermile.salamquran.Static.tag;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String appLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.appLanguage);
        try {
            if (appLanguage != null ){
                crateJsonFile();
                setFirstLanguage(appLanguage);

            }

        }catch (Exception error){
            Log.e(tag.error, "onCreate: ", error);
            Log.e(tag.publicsh, "onCreate: ", error);
            Log.e(tag.ac_Splash, "onCreate: ", error);
        }
    }





}
