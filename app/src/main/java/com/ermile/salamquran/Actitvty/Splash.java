package com.ermile.salamquran.Actitvty;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Function.FileManager.UnZipFile;
import com.ermile.salamquran.Function.Splash_function.ac_Splash;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
        String appLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.appLanguage);
            if (appLanguage != null ){
                new ac_Splash().crateJsonFile(this);
                new ac_Splash().setFirstLanguage(this,appLanguage);
            }
        Boolean file_isUnZiping = SaveManager.get(getApplicationContext()).getboolen_appINFO().get(SaveManager.fileIsUnZiping);
            if (!file_isUnZiping){
                new UnZipFile(this, file.comperes_File, format.zip);
                SaveManager.get(this).change_FileUnZip(true);
            }


        }catch (Exception error){
            Log.e(tag.error, "onCreate: ", error);
            Log.e(tag.publicsh, "onCreate: ", error);
            Log.e(tag.ac_Splash, "onCreate: ", error);
        }
    }





}
