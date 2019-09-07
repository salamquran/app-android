package com.ermile.salamquran.Actitvty.ac_Splash.Splash_function;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Actitvty.Intro;
import com.ermile.salamquran.Actitvty.Main;
import com.ermile.salamquran.Function.inApp.Dialog;
import com.ermile.salamquran.Function.HasConnection;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Static.lookServer;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;
import com.ermile.salamquran.Static.value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addUserTamp {

    Context context;

    public addUserTamp(Context context) {
        this.context = context;

        boolean introIsChacked = SaveManager.get(context).getboolen_appINFO().get(SaveManager.introIsChacked);

        if (new HasConnection().HasConnection(context)){
            if (userIsAdded(context)){
                Log.d(tag.ac_Splash, "User is Aded Tamp ");
                ((Activity) context).finish();
                context.startActivity(new Intent(context,Main.class));
            }
            else {
                ((Activity) context).finish();
                SingUpTamp(context,new Intent(context,Intro.class));
            }
        }
        else {
            if (!introIsChacked){
                Log.d(tag.ac_Splash, " Open Intro App ");
                SaveManager.get(context).change_firstOpen(false);
                ((Activity) context).finish();
                context.startActivity(new Intent(context,Intro.class));
            }
            else {
                Log.d(tag.ac_Splash, "no First Open ");
                ((Activity) context).finish();
                context.startActivity(new Intent(context,Main.class));
            }
        }
    }

    private Boolean userIsAdded(Context context) {
        String usercode = SaveManager.get(context).getstring_appINFO().get(SaveManager.userCode);
        String zoneid = SaveManager.get(context).getstring_appINFO().get(SaveManager.zoneID);
        String apikey = SaveManager.get(context).getstring_appINFO().get(SaveManager.apiKey);
        if (usercode == null)
        {
            return false;
        }
        else if (zoneid == null)
        {
            return false;
        }
        else if (apikey == null)
        {
            return false;
        }
        return true;
    }


    private void SingUpTamp(final Context context, final Intent StartIntentAfreAdd) {

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
                        Log.d(tag.ac_Splash, "Get Token For Add User Tamp ");
                        addUser(context,result.getString("token"),StartIntentAfreAdd);
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.error, "Get Token: "+msg_object );
                            Log.e(tag.ac_Splash, "Get Token: "+msg_object );
                            new Dialog(context,"Error Get Token",msg_object+"","OK",false, new Intent(context, getClass()));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(tag.error, "Get Token: ",e );
                    Log.e(tag.ac_Splash, "Get Token: ",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.e(tag.error, "Get Token: ",e );
                Log.e(tag.ac_Splash, "Get Token: ",e );
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

    /*Add User By Tokens (Auto)*/
    private void addUser(final Context context, final String token, final Intent intent){
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
                        SaveManager.get(context).change_infoLOGIN(apikey,usercode,zoneid);
                        SaveManager.get(context).change_firstOpen(false);
                        ((Activity) context).finish();
                        context.startActivity(intent);

                        Log.i(tag.ac_Splash,"USER ADED (Auto)"
                                +"\n usercode: "+usercode
                                +"\n zoneid: "+zoneid
                                +"\n apikey: "+apikey);
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.ac_Splash, "catch Add User Tamp: "+msg_object);
                            Log.e(tag.error, "catch Add User Tamp: "+msg_object);
                            new Dialog(context,"Error AddUser",msg_object+"","OK",false, new Intent(context, getClass()));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(tag.error, "Add User Tamp: ",e );
                    Log.e(tag.ac_Splash, "Add User Tamp: ",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.e(tag.error, "Add User Tamp: ",e );
                Log.e(tag.ac_Splash, "Add User Tamp: ",e );
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

        };AppContoroler.getInstance().addToRequestQueue(post_user_add);
    }


}
