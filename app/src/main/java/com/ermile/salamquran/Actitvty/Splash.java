package com.ermile.salamquran.Actitvty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Function.Utility.CheckVersion;
import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.ReadFile;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Function.Utility.WriteFile;
import com.ermile.salamquran.Function.api.GetAndroidDetail;
import com.ermile.salamquran.Function.api.SingUpUser;
import com.ermile.salamquran.Function.api.Token;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.charset;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;

import java.io.IOException;
import java.util.Locale;

import static com.ermile.salamquran.Function.Utility.SaveManager.appLanguage;

public class Splash extends AppCompatActivity {

    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(tag.activity, "onCreate: Splash");

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
            ReadFile.FromStorage(this,file.list_juz,format.json);
            ReadFile.FromStorage(this,file.list_sure,format.json);
            Log.d(tag.ac_Splash, "onCreate: file is UnZipped");
        } catch (IOException e) {
            FileManager.UnZipFile(this, file.comperes_File, format.zip);
            Log.d(tag.ac_Splash, "onCreate: Extracting... *(File Not! UnZipped)");
            e.printStackTrace();

        }
    }

    private void setAppLanguage(){
        String AppLanguage = SaveManager.get(this).getstring_appINFO().get(appLanguage);
        Log.d(tag.ac_Splash, "onCreate: AppLanguage: "+AppLanguage);
        if (AppLanguage == null){
            new WriteFile(getApplicationContext(), file.setting   ,  format.json,"" );
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
                SaveManager.get(this).change_LanguageByUser(true);
                setSettingApp();
                break;
        }
    }
    private void setSettingApp(){
        GetAndroidDetail.GetJson(this, new GetAndroidDetail.JsonLocalListener() {
            @Override
            public void onGetJson_Online(String ResponeOnline) {
                new WriteFile(getApplicationContext(),file.setting,format.json,ResponeOnline);
                Log.d(tag.ac_Splash, "online");
                choseLanguage();
            }

            @Override
            public void onGetJson_Offline(String ResponeOffline) {
                new WriteFile(getApplicationContext(),file.setting,format.json,ResponeOffline);
                Log.d(tag.ac_Splash, "Offline");
                choseLanguage();
            }

            @Override
            public void OnGetJson_OfflineNoNULL() {
                Log.d(tag.ac_Splash, "Offline");
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
            startActivity( new Intent(this, Language.class));
        }else {
            if (!CheckVersion.Deprecated(this)){
                singUpTemp();
            }
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
            Token.GetToken(new Token.TokenListener() {
                @Override
                public void onTokenRecieved(String token) {
                    addUserTamp(token);
                }

                @Override
                public void onTokenFailed(String error) {
                    Log.d(tag.ac_Splash, "Get Token Error: "+error);
                    nextActivity();
                }
            });
        }
        else {
            nextActivity();
        }
    }
    private void addUserTamp(String Token){
        SingUpUser.Singing(new SingUpUser.SingUpTampListener() {
            @Override
            public void UserAddToServer(Boolean UserAddToServer) {
                Log.d(tag.ac_Splash, "UserAddToServer: "+UserAddToServer);
                nextActivity();
            }

            @Override
            public void FiledUserAdd(Boolean FiledUserAdd) {
                Log.d(tag.ac_Splash, "UserAddToServer: "+FiledUserAdd);
                nextActivity();
            }
        }, getApplicationContext(), Token);
    }

    private void forceRunApp(){
        try
        {
            String settingApp = ReadFile.FromStorage(getApplicationContext(),file.setting,format.json);
            String AppLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.appLanguage);
            if (settingApp.length() < 20)
            {
                String valueJson = ReadFile.FromAsset(getApplicationContext(),AppLanguage,format.json, charset.UTF8);
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
            startActivity(new Intent(this, Main.class));
        }
        else {
            finish();
            startActivity(new Intent(this, Intro.class));
        }
    }


}
