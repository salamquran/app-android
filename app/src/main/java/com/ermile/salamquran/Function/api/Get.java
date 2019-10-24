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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Get {

    public static void app(final Context context, final Get_app_Listener getappListener){
        Log.d(tag.function, "Get > app");
        StringRequest get_local = new StringRequest(Request.Method.GET, url.setting, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                getappListener.onGetJson_Online(response);

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
                        getappListener.onGetJson_Offline(valueJson);
                    }else {
                        getappListener.OnGetJson_OfflineNoNULL();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        AppContoroler.getInstance().addToRequestQueue(get_local);

    }
    public interface Get_app_Listener {
        void onGetJson_Online(String ResponeOnline);
        void onGetJson_Offline(String ResponeOffline);
        void OnGetJson_OfflineNoNULL();
    }

    public static void qariList(final Get_qariList_Listener qariListListener){
        Log.d(tag.function, "Get > qariList");
        StringRequest get_local = new StringRequest(Request.Method.GET, url.qariList, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject main = new JSONObject(response);
                    if (main.getBoolean("ok")){
                        JSONArray result = main.getJSONArray("result");
                        qariListListener.response(String.valueOf(result));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    qariListListener.failed();
                }


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                qariListListener.failed();
            }
        });
        AppContoroler.getInstance().addToRequestQueue(get_local);
    }
    public interface Get_qariList_Listener {
        void response(String respone);
        void failed();
    }
}
