package com.ermile.salamquran.Function.GetJsonLocal;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Function.FileManager.LoadFromAsset;
import com.ermile.salamquran.Function.FileManager.ReadFile;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.charset;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import java.io.IOException;

public class JsonLocalFetcher {

    public static void GetJson(final Context context, final JsonLocalListener jsonLocalListener){
        Log.d(tag.ac_Splash, "GetJson: ");
        StringRequest get_local = new StringRequest(Request.Method.GET, url.setting, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                jsonLocalListener.onGetJson_Online(response);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                try
                {
                    String settingApp = new ReadFile().ReadFile(context,file.setting,format.json);
                    String AppLanguage = SaveManager.get(context).getstring_appINFO().get(SaveManager.appLanguage);
                    if (settingApp.length() < 20)
                    {
                        String valueJson = new LoadFromAsset().LoadFromAsset(context,AppLanguage,format.json, charset.UTF8);
                        jsonLocalListener.onGetJson_Offline(valueJson);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        AppContoroler.getInstance().addToRequestQueue(get_local);
    }
}
