package com.ermile.salamquran.Actitvty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Function.FileManager.UnZipFile;
import com.ermile.salamquran.Function.FileManager.WriteFile;
import com.ermile.salamquran.Function.GetJsonLocal.JsonLocalFetcher;
import com.ermile.salamquran.Function.GetJsonLocal.JsonLocalListener;
import com.ermile.salamquran.Function.GetToken.TokenFetcher;
import com.ermile.salamquran.Function.GetToken.TokenListener;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Function.SingUpTamp.SingUpTamp;
import com.ermile.salamquran.Function.SingUpTamp.SingUpTampListener;
import com.ermile.salamquran.Function.inApp.CheckVersion;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;

import java.util.Locale;

import static com.ermile.salamquran.Function.SaveManager.appLanguage;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(tag.activity, "onCreate: Splash");

        try {
            unZipnigDatabase();
            setAppLanguage();
        }
        catch (Exception error){
            Log.e(tag.ac_Splash, "onCreate: ", error);
            Log.e(tag.error, "onCreate: ", error);
            Log.e(tag.publicsh, "onCreate: ", error);
        }

    }


    private void unZipnigDatabase(){
        Boolean file_isUnZiping = SaveManager.get(getApplicationContext()).getboolen_appINFO().get(SaveManager.fileIsUnZiping);
        Log.d(tag.ac_Splash, "onCreate: file is UnZiping: "+file_isUnZiping);
        if (!file_isUnZiping){
            new UnZipFile(this, file.comperes_File, format.zip);
            SaveManager.get(this).change_FileUnZip(true);
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
        JsonLocalFetcher.GetJson(this, new JsonLocalListener() {
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
            finish();
            startActivity( new Intent(this, Language.class));
        }else {
            if (!new CheckVersion().Deprecated(this)){
                singUpTamp();
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
    private void singUpTamp(){
        if ( !userIsAdded() ) {
            TokenFetcher.GetToken(new TokenListener() {
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
        SingUpTamp.Sining(new SingUpTampListener() {
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



    private void nextActivity(){
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
