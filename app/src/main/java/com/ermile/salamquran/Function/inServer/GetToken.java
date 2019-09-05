package com.ermile.salamquran.Function.inServer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.look;
import com.ermile.salamquran.Static.url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetToken extends AsyncTask<String, String , String> {

    Context context;

    public GetToken(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {
        for (String p : params) {
            String Localid = p;

            StringRequest getToken = new StringRequest(Request.Method.POST, url.token, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    JSONObject mainObject,result;
                    JSONArray msg;
                    boolean ok_getToken;
                    try {
                        mainObject = new JSONObject(response);
                        ok_getToken = mainObject.getBoolean("ok");
                        if (ok_getToken){
                            result = mainObject.getJSONObject("result");
                            String token = result.getString("token");
                            return token;
                        }else {
                            msg = mainObject.getJSONArray("msg");
                            for (int i = 0 ; i<= msg.length();i++){
                                JSONObject msg_object = msg.getJSONObject(i);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            })
                    // Send Headers
            {
                @Override
                public Map<String, String> getHeaders()  {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("appkey", look.appkey);
                    return headers;
                }

            };
            AppContoroler.getInstance().addToRequestQueue(getToken);

        }
        return null;
    }
}