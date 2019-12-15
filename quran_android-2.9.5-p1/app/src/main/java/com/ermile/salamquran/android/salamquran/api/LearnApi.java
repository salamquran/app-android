package com.ermile.salamquran.android.salamquran.api;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.salamquran.User;
import com.ermile.salamquran.android.salamquran.url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LearnApi {

  public static void group(Context context, groupListener listener){
    StringRequest newsRQ = new StringRequest(Request.Method.GET, url.getLmsGroup(context), response -> {
      try {
        JSONObject mainObject = new JSONObject(response);
        if (!mainObject.isNull("ok") && mainObject.getBoolean("ok") ) {
          if (!mainObject.isNull("result")){
            JSONArray result = mainObject.getJSONArray("result");
            listener.onReceived(String.valueOf(result));
          }
        }else {
          listener.onFiled();
        }

      } catch (JSONException e) {
        e.printStackTrace();
        listener.onFiled();
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError e) {
        listener.onFiled();
      }
    })
    {
      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", User.getApikey(context));
        return headers;
      }
    };
    newsRQ.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    QuranApplication.getInstance().addToRequestQueue(newsRQ);

  }
  public interface groupListener{
    void onReceived(String Result);
    void onFiled();
  }


  public static void levelList(Context context,String id, levelListListener listener){
    StringRequest newsRQ = new StringRequest(Request.Method.GET, url.getLmsLevelList(context,id), response -> {
      try {
        JSONObject mainObject = new JSONObject(response);
        if (!mainObject.isNull("ok") && mainObject.getBoolean("ok") ) {
          if (!mainObject.isNull("result")){
            JSONArray result = mainObject.getJSONArray("result");
            listener.onReceived(String.valueOf(result));
          }
        }else {
          listener.onFiled();
        }

      } catch (JSONException e) {
        e.printStackTrace();
        listener.onFiled();
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError e) {
        listener.onFiled();
      }
    })
    {
      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", User.getApikey(context));
        return headers;
      }
    };
    newsRQ.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    QuranApplication.getInstance().addToRequestQueue(newsRQ);

  }
  public interface levelListListener{
    void onReceived(String Result);
    void onFiled();
  }


}
