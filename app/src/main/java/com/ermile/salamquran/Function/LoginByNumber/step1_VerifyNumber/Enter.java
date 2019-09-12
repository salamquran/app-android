package com.ermile.salamquran.Function.LoginByNumber.step1_VerifyNumber;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Enter {

    public static void sendNumberToVerify(final String tokens , final String phoneNumber, final String apiKey, final EnterListener enterListener){
        StringRequest getVerify = new StringRequest(Request.Method.POST, url.enter, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject;
                JSONArray msg;
                boolean ok_getVerify;
                try {
                    mainObject = new JSONObject(response);
                    ok_getVerify = mainObject.getBoolean("ok");
                    if (ok_getVerify){
                        enterListener.SendCodeToNumber();
                    }else {
                        enterListener.ErrorSendCodeToNumber("error > VerifyNumberFetcher HasNet");
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.error,"VerifyNumberFetcher HasNet: "+msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    enterListener.ErrorSendCodeToNumber("error > VerifyNumberFetcher "+e);
                    e.printStackTrace();
                    Log.e(tag.error,"VerifyNumberFetcher >JSONException "+e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                enterListener.ErrorSendCodeToNumber("error > VerifyNumberFetcher "+error);
                Log.e(tag.error,"VerifyNumberFetcher > onErrorResponse  "+error);
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("token", tokens);
                headers.put("apikey", apiKey);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> posting = new HashMap<>();
                posting.put("mobile", phoneNumber);
                return posting;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getVerify);

    }
}
