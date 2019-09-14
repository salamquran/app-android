package com.ermile.salamquran.Function.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Function.Utility.ReadFile;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.charset;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import java.io.IOException;

public class GetAndroidDetail {

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
                    String settingApp = ReadFile.FromStorage(context,file.setting,format.json);
                    String AppLanguage = SaveManager.get(context).getstring_appINFO().get(SaveManager.appLanguage);
                    if (settingApp.length() < 20)
                    {
                        String valueJson = ReadFile.FromAsset(context,AppLanguage,format.json, charset.UTF8);
                        jsonLocalListener.onGetJson_Offline(valueJson);
                    }else {
                        jsonLocalListener.OnGetJson_OfflineNoNULL();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        AppContoroler.getInstance().addToRequestQueue(get_local);

    }

    public interface JsonLocalListener {

        void onGetJson_Online(String ResponeOnline);

        void onGetJson_Offline(String ResponeOffline);

        void OnGetJson_OfflineNoNULL();
    }
}
