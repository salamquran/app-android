package com.ermile.salamquran.Function.GetToken;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

public class TokenFetcher {

    public static void GetToken(final TokenListener tokenListener) {

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
                        tokenListener.onTokenRecieved(result.getString("token"));
                        log.d(this.getClass().getName(),"Get Token"," :) Token is Hidden..");
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            tokenListener.onTokenFailed(msg_object+"");
                            Log.e(tag.error, "Get Token : \n msg"+ msg_object );

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    tokenListener.onTokenFailed("JSONException: "+e);
                    Log.e(tag.error, "Get Token : \nJSONException",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                tokenListener.onTokenFailed("VolleyError: "+e);
                Log.e(tag.error, "Get Token : \nVolleyError",e );
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("appkey", lookServer.appkey);
                return headers;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getToken);

    }
}

