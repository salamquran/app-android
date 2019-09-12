package com.ermile.salamquran.Function.LoginByNumber.step2_VerifyCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VerifyCodeFethcer {

    public static void vrifyCode(final Context context, final String token, final String apiKey, final String numberPhone, final String verifyCode, final Intent intent, final VerifuCodeListener verifuCodeListener){
        StringRequest getVerify = new StringRequest(Request.Method.POST, url.verify, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject,result;
                JSONArray msg;
                boolean ok_getVerify;
                try {
                    mainObject = new JSONObject(response);
                    ok_getVerify = mainObject.getBoolean("ok");
                    if (ok_getVerify){
                        result = mainObject.getJSONObject("result");
                        String apikeyNew = result.getString("apikey");
                        String usercode  =  SaveManager.get(context).getstring_appINFO().get(SaveManager.userCode);
                        String zoneid    =  SaveManager.get(context).getstring_appINFO().get(SaveManager.zoneID);

                        verifuCodeListener.VerifyCode(apikeyNew,zoneid,usercode);

                        ((Activity)context).finish();
                        context.startActivity(intent);

                    }else {
                        verifuCodeListener.ErrorGetVerifyCode("VerifyCodeFethcer Error Has Net");
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.error,"VerifyCodeFethcer: "+ msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    verifuCodeListener.ErrorGetVerifyCode("VerifyCodeFethcer Error "+e);
                    e.printStackTrace();
                    Log.e(tag.error,"VerifyCodeFethcer >JSONException "+e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                verifuCodeListener.ErrorGetVerifyCode("VerifyCodeFethcer Error "+error);
                Log.e(tag.error,"VerifyCodeFethcer > onErrorResponse  "+error);
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("token", token);
                headers.put("apikey", apiKey);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> posting = new HashMap<>();
                posting.put("mobile", numberPhone);
                posting.put("verifycode", verifyCode);
                return posting;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getVerify);
    }
}
