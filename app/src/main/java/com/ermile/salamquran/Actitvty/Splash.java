package com.ermile.salamquran.Actitvty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ermile.salamquran.Function.FileManager.WriteFile;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.SaveManager;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.value;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String appLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.appLanguage);
        try {
            if (appLanguage != null ){
                crateJsonFile();

            }

        }catch (Exception error){
            Log.e(tag.error, "onCreate: ", error);
            Log.e(tag.publicsh, "onCreate: ", error);
            Log.e(tag.ac_Splash, "onCreate: ", error);
        }
    }

    private void crateJsonFile(){
        new WriteFile(getApplicationContext(), value.jsonFile_local, format.json,null);
        new WriteFile(getApplicationContext(),value.jsonFile_JuzSura,format.json,null);
        new WriteFile(getApplicationContext(),value.jsonFile_JuzHezb,format.json,null);
    }
}
