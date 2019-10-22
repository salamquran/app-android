package com.ermile.salamquran.Function.api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.log;
import com.ermile.salamquran.Static.lookServer;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LMS_api {

    public static void getGroupList(final Context context, final groupListListener listListener){
        StringRequest getToken = new StringRequest(Request.Method.GET, url.lms_group, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject;
                JSONArray msg,result;
                boolean ok_getToken;
                try {
                    mainObject = new JSONObject(response);
                    ok_getToken = mainObject.getBoolean("ok");
                    if (ok_getToken){
                        result = mainObject.getJSONArray("result");
                        listListener.onGetGroupList(String.valueOf(result));
                        log.d(this.getClass().getName(),"Get Token"," :) Token is Hidden..");
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            listListener.onErrorGroupList(msg_object+"");
                            Log.e(tag.error, "LMS_api: \n msg"+ msg_object );

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    listListener.onErrorGroupList("JSONException: "+e);
                    Log.e(tag.error, "LMS_api : \nJSONException",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                listListener.onErrorGroupList("VolleyError: "+e);
                Log.e(tag.error, "LMS_api : \nVolleyError",e );
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("apikey", SaveManager.get(context).getstring_appINFO().get(SaveManager.apiKey));
                return headers;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getToken);
    }

    public interface groupListListener {
        void onGetGroupList(String token);

        void onErrorGroupList(String error);
    }

    public static void getLevelList(final Context context,int idGroup, final levelList_ListListener listListener){
        StringRequest getToken = new StringRequest(Request.Method.GET, url.lms_levelList+"?id="+idGroup, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject;
                JSONArray msg,result;
                boolean ok;
                try {
                    mainObject = new JSONObject(response);
                    ok = mainObject.getBoolean("ok");
                    if (ok){
                        result = mainObject.getJSONArray("result");
                        listListener.onGetLevelList(String.valueOf(result));
                        log.d(this.getClass().getName(),"Get Token"," :) Token is Hidden..");
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            listListener.onErrorLevelList(msg_object+"");
                            Log.e(tag.error, "LMS_api : \n msg"+ msg_object );

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    listListener.onErrorLevelList("JSONException: "+e);
                    Log.e(tag.error, "LMS_api : \nJSONException",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                listListener.onErrorLevelList("VolleyError: "+e);
                Log.e(tag.error, "LMS_api : \nVolleyError",e );
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("apikey", SaveManager.get(context).getstring_appINFO().get(SaveManager.apiKey));
                return headers;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getToken);
    }

    public interface levelList_ListListener {
        void onGetLevelList(String token);

        void onErrorLevelList(String error);
    }

    public static void getLevel(final Context context,String idLevel, final level_ListListener listListener){
        StringRequest getToken =
                new StringRequest(Request.Method.GET,
                url.lms_level+"?id="+idLevel,
                new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject;
                JSONArray msg,result;
                boolean ok;
                try {
                    mainObject = new JSONObject(response);
                    ok = mainObject.getBoolean("ok");
                    if (ok){
                        result = mainObject.getJSONArray("result");
                        listListener.onGetLevel(String.valueOf(result));
                        log.d(this.getClass().getName(),"Get Token"," :) Token is Hidden..");
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            listListener.onErrorLevel(msg_object+"");
                            Log.e(tag.error, "LMS_api : \n msg"+ msg_object );

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    listListener.onErrorLevel("JSONException: "+e);
                    Log.e(tag.error, "LMS_api : \nJSONException",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                listListener.onErrorLevel("VolleyError: "+e);
                Log.e(tag.error, "LMS_api : \nVolleyError",e );
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("apikey", SaveManager.get(context).getstring_appINFO().get(SaveManager.apiKey));
                return headers;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getToken);
    }

    public interface level_ListListener {
        void onGetLevel(String token);

        void onErrorLevel(String error);
    }
}
