package com.ermile.salamquran.Function.Splash_function;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Actitvty.Language;
import com.ermile.salamquran.Function.FileManager.LoadFromAsset;
import com.ermile.salamquran.Function.FileManager.ReadFile;
import com.ermile.salamquran.Function.FileManager.WriteFile;
import com.ermile.salamquran.Function.inApp.HasConnection;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.SaveManager;
import com.ermile.salamquran.Static.charset;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.url;

import java.io.IOException;
import java.util.Locale;

public class ac_Splash {

    public void crateJsonFile(Context context){
        new WriteFile(context, file.setting,  format.json,null);
        new WriteFile(context, file.list_sure,format.json,null);
        new WriteFile(context, file.list_juz, format.json,null);
    }

    /** Set First Language Auto from Device */
    /*Set First Language*/
    public void setFirstLanguage(Context context,String AppLanguage){
        String language_device = Locale.getDefault().getLanguage();
        switch (language_device){
            case "en":
                SaveManager.get(context).change_appLanguage(language_device);
                SaveManager.get(context).change_LanguageByUser(true);

                getSettingApp(context,AppLanguage);
                break;
            default:
                SaveManager.get(context).change_appLanguage(language_device);
                SaveManager.get(context).change_LanguageByUser(false);

                getSettingApp(context,AppLanguage);
                break;
        }
    }

    private void setLanguageByUser(Context context ,Boolean changeLanguageByUser){
        if (changeLanguageByUser){
            context.startActivity( new Intent(context,Language.class));
        }else {
            new CheckVersion(context);
        }

    }

    /** Get Setting Json Online(if connected) & Offline(if null)*/
    public void getSettingApp(final Context context, String AppLanguage){
        final boolean changeLanguageUser= SaveManager.get(context).getboolen_appINFO().get(SaveManager.changeLanguageByUser);
        if (new HasConnection().HasConnection(context))
        {
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

                }
            });
            AppContoroler.getInstance().addToRequestQueue(get_local);
        }
        else
        {
            try
            {
                String settingApp = new ReadFile().ReadFile(context,file.setting,format.json);
                if (settingApp == null)
                {
                    String valueJson = new LoadFromAsset().LoadFromAsset(context,AppLanguage,format.json, charset.UTF8);
                    new WriteFile(context,file.setting,format.json,valueJson);
                    setLanguageByUser(context,changeLanguageUser);
                }
                else
                {
                    setLanguageByUser(context,changeLanguageUser);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
