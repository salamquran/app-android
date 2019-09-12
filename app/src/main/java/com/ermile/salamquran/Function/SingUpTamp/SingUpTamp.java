package com.ermile.salamquran.Function.SingUpTamp;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Static.log;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;
import com.ermile.salamquran.Static.value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SingUpTamp {

    public static void Sining(final SingUpTampListener singUpTampListener, final Context context, final String token){
        /*Get Info Device*/
        final String model = Build.MODEL;
        final String serial = Build.SERIAL;
        final String manufacturer = Build.MANUFACTURER;
        final String hardware = Build.HARDWARE;
        final String type = Build.TYPE;
        final String board = Build.BOARD;
        final String id = Build.ID;
        final String product =  Build.PRODUCT;
        final String device = Build.DEVICE;
        final String brand = Build.BRAND;
        /*Request*/
        StringRequest post_user_add = new StringRequest(Request.Method.POST, url.user_add, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject,result ;
                JSONArray msg;
                boolean ok_getToken;
                try {
                    mainObject = new JSONObject(response);
                    ok_getToken = mainObject.getBoolean("ok");
                    if (ok_getToken){
                        result = mainObject.getJSONObject("result");
                        String usercode = result.getString("usercode");
                        String zoneid = result.getString("zoneid");
                        String apikey = result.getString("apikey");
                        /*Save Value*/
                        Log.d(tag.ac_Splash, "onResponse: "+usercode+" | "+zoneid+" | "+apikey);
                        SaveManager.get(context).change_infoLOGIN(apikey,usercode,zoneid);
                        singUpTampListener.UserAddToServer(true);


                        log.d(this.getClass().getName(),"Add User",usercode+"|"+zoneid+"|"+apikey);
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        singUpTampListener.FiledUserAdd(false);
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.error, "catch Add User Tamp: "+msg_object);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    singUpTampListener.FiledUserAdd(false);
                    Log.e(tag.error, "Add User Tamp: ",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                singUpTampListener.FiledUserAdd(false);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("token", token );
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> posting = new HashMap<>();
                posting.put("model", model );
                posting.put("serial", serial );
                posting.put("manufacturer", manufacturer );
                posting.put("version", value.versionName );
                posting.put("hardware", hardware );
                posting.put("type", type );
                posting.put("board", board );
                posting.put("id", id );
                posting.put("product", product );
                posting.put("device", device );
                posting.put("brand", brand );
                return posting;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(post_user_add);
    }
}
