package com.ermile.salamquran.Actitvty.ac_Splash.Splash_function;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Actitvty.Language;
import com.ermile.salamquran.Function.FileManager.LoadFromAsset;
import com.ermile.salamquran.Function.FileManager.ReadFile;
import com.ermile.salamquran.Function.FileManager.WriteFile;
import com.ermile.salamquran.Function.HasConnection;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Function.inApp.CheckVersion;
import com.ermile.salamquran.Function.inApp.Dialog;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.charset;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import java.io.IOException;
import java.util.Locale;

public class guide_Splash {


    /** Set First Language Auto from Device */
    /*Set First Language*/
    public void setFirstLanguage(Context context,String AppLanguage){
        String language_device = Locale.getDefault().getLanguage();
        Log.d(tag.ac_Splash, "setFirstLanguage: >language_device = "+language_device);
        switch (language_device){
            case "fa":
            case "ar":
                SaveManager.get(context).change_appLanguage(language_device);
                SaveManager.get(context).change_LanguageByUser(false);
                getSettingApp(context,language_device);
                break;
            default:
                SaveManager.get(context).change_appLanguage("en");
                SaveManager.get(context).change_LanguageByUser(true);

                getSettingApp(context,"en");
                break;
        }
    }

    /** Get Setting Json Online(if connected) & Offline(if null)*/
    public void getSettingApp(final Context context, final String AppLanguage){
        final boolean changeLanguageUser= SaveManager.get(context).getboolen_appINFO().get(SaveManager.changeLanguageByUser);
        if (new HasConnection().HasConnection(context))
        {
            Log.d(tag.ac_Splash, "getSettingApp: Has Internet");
            /*Get Setting From Url*/
            StringRequest get_local = new StringRequest(Request.Method.GET, url.setting, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    new WriteFile(context,file.setting,format.json,response);
                    setLanguageByUser(context,changeLanguageUser);

                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    new Dialog(context,"Error Network","Please try again","ok",false,new Intent(context,context.getClass()));
                    Log.e(tag.error, "Get Setting Online onErrorResponse: Has Internet ", error);
                    Log.e(tag.ac_Splash, "onErrorResponse: Has Internet ", error);
                }
            });
            AppContoroler.getInstance().addToRequestQueue(get_local);
        }
        else
        {
           setSetting_Offline(context,AppLanguage,changeLanguageUser);
        }
    }

    private void setSetting_Offline(Context context,String AppLanguage,Boolean changeLanguageUser){
        try
        {
            Log.d(tag.ac_Splash, "getSettingApp: No Internet ");
            String settingApp = new ReadFile().ReadFile(context,file.setting,format.json);
            if (settingApp.length() < 20)
            {
                Log.d(tag.ac_Splash, "getSettingApp: " + AppLanguage+format.json +" "+charset.UTF8);
                String valueJson = new LoadFromAsset().LoadFromAsset(context,AppLanguage,format.json, charset.UTF8);
                new WriteFile(context,file.setting,format.json,valueJson);
                Log.d(tag.ac_Splash, "getSettingApp: File setting.json == Null \n Value set to File IS: "+valueJson);
                setLanguageByUser(context,changeLanguageUser);
            }
            else
            {
                Log.d(tag.ac_Splash, "getSettingApp: File setting.json != Null ");
                setLanguageByUser(context,changeLanguageUser);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.e(tag.ac_Splash, "getSettingApp: ",e );
            Log.e(tag.error, "getSettingApp: ",e );
        }
    }

    private void setLanguageByUser(Context context ,Boolean changeLanguageByUser){
        Log.d(tag.ac_Splash, "setLanguageByUser: "+changeLanguageByUser);
        if (changeLanguageByUser){
            Log.d(tag.ac_Splash, "Go to Language.class ");
            ((Activity) context).finish();
            context.startActivity( new Intent(context,Language.class));
        }else {
            if (!new CheckVersion().Deprecated(context)){
                new addUserTamp(context);
            }
        }
    }
}
