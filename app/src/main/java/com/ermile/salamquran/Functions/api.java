package com.ermile.salamquran.Functions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Functions.Interface.api_interface;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.Value.file;
import com.ermile.salamquran.Value.format;
import com.ermile.salamquran.Value.lookServer;
import com.ermile.salamquran.Value.statics;
import com.ermile.salamquran.Value.tag;
import com.ermile.salamquran.Value.url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class api {

    public static void ghariList(final api_interface.ghariList_Listener qariListListener){
        Log.d(tag.function, "Get > ghariList");
        if (!FileManager.findFile_storage("list/","ghariList"+format.json)){
            StringRequest get_local = new StringRequest(Request.Method.GET, url.qariList, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject main = new JSONObject(response);
                        if (main.getBoolean("ok")){
                            JSONArray result = main.getJSONArray("result");
                            FileManager.write_InStorage("list/","ghariList",format.json,String.valueOf(result));
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
        else {
            File file = FileManager.getFile_storage("list/","ghariList"+format.json);
            try {
                String result = FileManager.readFile(file);
                qariListListener.response(result);
            }
            catch (IOException e) {
                qariListListener.failed();
                e.printStackTrace();
            }
        }

    }

    /**
     * Get App Detail Json
     */
    public static void appDetail(final Context context, final api_interface.appDetail_Listener getappListener){
        Log.d(tag.function, "Get > app");
        StringRequest get_local = new StringRequest(Request.Method.GET, url.setting, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                getappListener.onRecive_Online(response);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                try
                {
                    String settingApp = FileManager.read_FromStorage(context, file.setting, format.json);
                    String AppLanguage = SaveManager.get(context).getstring_appINFO().get(SaveManager.appLanguage);
                    if (settingApp.length() < 20)
                    {
                        String valueJson = FileManager.read_FromAsset(context,AppLanguage,format.json, statics.UTF8);
                        getappListener.onRecive_Offline(valueJson);
                    }else {
                        getappListener.onRecive_OfflineNoNULL();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        get_local.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppContoroler.getInstance().addToRequestQueue(get_local);

    }

    /**
     * Get Token & Singing User
     */
    public static void Token(final api_interface.token_Listener token_Listener){
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
                        token_Listener.onRecive(result.getString("token"));
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            token_Listener.onFailed(msg_object+"");
                            Log.e(tag.error, "Get Token : \n msg"+ msg_object );
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    token_Listener.onFailed("JSONException: "+e);
                    Log.e(tag.error, "Get Token : \nJSONException",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                token_Listener.onFailed("VolleyError: "+e);
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
        getToken.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppContoroler.getInstance().addToRequestQueue(getToken);
    }

    public static void Singing(final Context context,
                               final String token ,
                               final api_interface.singUp_Listener singUpTampListener)
    {
        /*Get Info Device*/
        final String model = Build.MODEL;
        @SuppressLint("HardwareIds") final String serial = Build.SERIAL;
        final String manufacturer = Build.MANUFACTURER;
        final String hardware = Build.HARDWARE;
        final String type = Build.TYPE;
        final String board = Build.BOARD;
        final String id = Build.ID;
        final String product = Build.PRODUCT;
        final String device = Build.DEVICE;
        final String brand = Build.BRAND;
        /*Request*/
        StringRequest post_user_add = new StringRequest(
                Request.Method.POST,
                url.user_add,
                new Response.Listener<String>()
                {
            @Override
            public void onResponse(String response) {
                JSONObject mainObject, result;
                JSONArray msg;
                boolean ok_getToken;
                try {
                    mainObject = new JSONObject(response);
                    ok_getToken = mainObject.getBoolean("ok");
                    if (ok_getToken) {
                        result = mainObject.getJSONObject("result");
                        String usercode = result.getString("usercode");
                        String zoneid = result.getString("zoneid");
                        String apikey = result.getString("apikey");
                        /*Save Value*/
                        Log.d(tag.ac_Splash, "onResponse: " + usercode + " | " + zoneid + " | " + apikey);
                        SaveManager.get(context).change_infoLOGIN(apikey, usercode, zoneid);
                        singUpTampListener.onRecive(true);


                        Log.d(tag.api, "User Info: usercode: " + usercode + " | zonID: " + zoneid + " | apikey: " + apikey);
                    } else {
                        msg = mainObject.getJSONArray("msg");
                        singUpTampListener.onFailed(false);
                        for (int i = 0; i <= msg.length(); i++) {
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.error, "catch Add User Tamp: " + msg_object);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    singUpTampListener.onFailed(false);
                    Log.e(tag.error, "Add User Tamp: ", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                singUpTampListener.onFailed(false);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("token", token);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> posting = new HashMap<>();
                posting.put("model", model);
                posting.put("serial", serial);
                posting.put("manufacturer", manufacturer);
                posting.put("version", statics.versionName);
                posting.put("hardware", hardware);
                posting.put("type", type);
                posting.put("board", board);
                posting.put("id", id);
                posting.put("product", product);
                posting.put("device", device);
                posting.put("brand", brand);
                return posting;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(post_user_add);
    }
}
