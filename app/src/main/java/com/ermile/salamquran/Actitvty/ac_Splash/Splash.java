package com.ermile.salamquran.Actitvty.ac_Splash;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Actitvty.ac_Splash.Splash_function.guide_Splash;
import com.ermile.salamquran.Function.FileManager.UnZipFile;
import com.ermile.salamquran.Function.FileManager.WriteFile;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean firstOpen = SaveManager.get(this).getboolen_appINFO().get(SaveManager.firstOpen);
        try {
            Log.d(tag.activity, "onCreate: Splash");
            Boolean file_isUnZiping = SaveManager.get(getApplicationContext()).getboolen_appINFO().get(SaveManager.fileIsUnZiping);
            Log.d(tag.ac_Splash, "onCreate: file is UnZiping: "+file_isUnZiping);
            if (!file_isUnZiping){
                new UnZipFile(this, file.comperes_File, format.zip);
                SaveManager.get(this).change_FileUnZip(true);
            }

            String appLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.appLanguage);
            if (appLanguage == null ){
                Log.d(tag.ac_Splash, "onCreate: AppLanguage: "+appLanguage);
                new WriteFile(getApplicationContext(), file.setting   ,  format.json,"" );
                new WriteFile(getApplicationContext(), file.list_sure ,  format.json,"" );
                new WriteFile(getApplicationContext(), file.list_juz  ,  format.json,"" );
                new guide_Splash().setFirstLanguage(this,appLanguage);
            }else {
                Log.d(tag.ac_Splash, "onCreate: AppLanguage: "+appLanguage);
                new guide_Splash().getSettingApp(this,appLanguage);
            }
        }
        catch (Exception error){
            Log.e(tag.ac_Splash, "onCreate: ", error);
            Log.e(tag.error, "onCreate: ", error);
            Log.e(tag.publicsh, "onCreate: ", error);
        }



    }
}
