package com.ermile.salamquran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.ermile.salamquran.Functions.FileManager;
import com.ermile.salamquran.Functions.Interface.api_interface;
import com.ermile.salamquran.Functions.SaveManager;
import com.ermile.salamquran.Functions.api;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Value.file;
import com.ermile.salamquran.Value.format;
import com.ermile.salamquran.Value.tag;

import java.io.IOException;
import java.util.Locale;

import static com.ermile.salamquran.Functions.SaveManager.appLanguage;

public class Splash extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Splash.this, "Next", Toast.LENGTH_SHORT).show();
                forceRunApp();
            }
        };

        try {
            handler.postDelayed(runnable,10*1000);
            unZipnigDatabase();
            setAppLanguage();
        }
        catch (Exception error){
            Toast.makeText(this, "Error Runnig", Toast.LENGTH_SHORT).show();
            finish();
            Log.e(tag.ac_Splash, "onCreate: ", error);
            Log.e(tag.error, "onCreate: ", error);
            Log.e(tag.publicsh, "onCreate: ", error);
        }
    }

    private void unZipnigDatabase(){
        try {
            FileManager.read_FromStorage(this, file.list_juz, format.json);
            FileManager.read_FromStorage(this,file.list_sure,format.json);
            Log.d(tag.ac_Splash, "onCreate: file is UnZipped");
        } catch (IOException e) {
            FileManager.UnZip_FromAssets(this, file.comperes_File, format.zip);
            Log.d(tag.ac_Splash, "onCreate: Extracting... *(File Not! UnZipped)");
            e.printStackTrace();

        }
    }

    private void setAppLanguage(){
        String AppLanguage = SaveManager.get(this).getstring_appINFO().get(appLanguage);
        Log.d(tag.ac_Splash, "onCreate: AppLanguage: "+AppLanguage);
        if (AppLanguage == null){
            FileManager.write_OutStorage(getApplicationContext(), file.setting   ,  format.json,"" );
            setFirstLanguages();
        }
        else {
            setSettingApp();
        }

    }
    private void setFirstLanguages(){
        String language_device = Locale.getDefault().getLanguage();
        Log.d(tag.ac_Splash, "setFirstLanguage: >language_device = "+language_device);
        switch (language_device){
            case "fa":
            case "ar":
                SaveManager.get(this).change_appLanguage(language_device);
                SaveManager.get(this).change_LanguageByUser(false);
                setSettingApp();
                break;
            default:
                SaveManager.get(this).change_appLanguage("en");
                SaveManager.get(this).change_LanguageByUser(false);
                setSettingApp();
                break;
        }
    }
    private void setSettingApp(){
        api.appDetail(getApplication(), new api_interface.appDetail_Listener() {
            @Override
            public void onRecive_Online(String ResponeOnline) {
                FileManager.write_OutStorage(getApplicationContext(),file.setting,format.json,ResponeOnline);
                Log.d(tag.ac_Splash, "online");
                choseLanguage();
            }

            @Override
            public void onRecive_Offline(String ResponeOffline) {
                FileManager.write_OutStorage(getApplicationContext(),file.setting,format.json,ResponeOffline);
                Log.d(tag.ac_Splash, "Offline");
                choseLanguage();
            }

            @Override
            public void onRecive_OfflineNoNULL() {
                Log.d(tag.ac_Splash, "Offline and Json Not Null");
                choseLanguage();
            }
        });
    }
    private void choseLanguage(){
        Boolean changeLanguageByUser = SaveManager.get(this).getboolen_appINFO().get(SaveManager.changeLanguageByUser);
        Log.d(tag.ac_Splash, "changeLanguageByUser: "+changeLanguageByUser);
        if (changeLanguageByUser){
            handler.removeCallbacks(runnable);
            finish();
            startActivity( new Intent(this, ChangeLanguage.class));
        }else {
            singUpTemp();
            /*if (!CheckVersion.Deprecated(this)){
                singUpTemp();
            }*/
        }
    }
    private Boolean userIsAdded() {
        String usercode = SaveManager.get(this).getstring_appINFO().get(SaveManager.userCode);
        String zoneid = SaveManager.get(this).getstring_appINFO().get(SaveManager.zoneID);
        String apikey = SaveManager.get(this).getstring_appINFO().get(SaveManager.apiKey);
        if (usercode == null || zoneid == null || apikey == null)
        {
            Log.d(tag.ac_Splash, "user Not Added usercode: "+usercode+" |zonID: "+zoneid+" |apikey: "+apikey);
            return false;
        }
        Log.d(tag.ac_Splash, "user Is Added usercode: "+usercode+" |zonID: "+zoneid+" |apikey: "+apikey);
        return true;
    }
    private void singUpTemp(){
        if ( !userIsAdded() ) {
            api.Token(new api_interface.token_Listener() {
                @Override
                public void onRecive(String result) {
                    addUserTamp(result);
                }

                @Override
                public void onFailed(String result) {
                    Log.d(tag.ac_Splash, "Get Token Error: "+result);
                    nextActivity();
                }
            });
        }
        else {
            nextActivity();
        }
    }
    private void addUserTamp(String Token){
        api.Singing(getApplication() , Token,new api_interface.singUp_Listener() {
            @Override
            public void onRecive(Boolean result) {
                Log.d(tag.ac_Splash, "UserAddToServer: "+result);
                nextActivity();
            }

            @Override
            public void onFailed(Boolean result) {
                Log.d(tag.ac_Splash, "UserAddToServer: "+result);
                nextActivity();
            }
        });
    }

    private void forceRunApp(){
        try
        {
            String settingApp = FileManager.read_FromStorage(getApplicationContext(),file.setting,format.json);
            String AppLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(appLanguage);
            if (settingApp.length() < 20)
            {
//                String valueJson = FileManager.read_FromAsset(getApplicationContext(),AppLanguage,format.json, statics.UTF8);
                nextActivity();
            }else {
                nextActivity();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextActivity(){
        handler.removeCallbacks(runnable);
        Boolean intro_isChecked = SaveManager.get(this).getboolen_appINFO().get(SaveManager.introIsChacked);
        if (intro_isChecked){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        else {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

}
