package com.ermile.salamquran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.network.AppContoroler;
import com.ermile.salamquran.saveData.SessionManager;
import com.ermile.salamquran.saveData.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class Splash extends AppCompatActivity { private static String TAG = "Splash";
    /*Get language device*/
    private String language_device = Locale.getDefault().getLanguage();
    /*Value*/
    Value value = new Value();
    /*Object's of activity_splash.xml*/
    ImageView logo;
    TextView title,desc,titleProgress;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            writeToMyFile("1234444");
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            value.versionAPK = pInfo.versionName;
            value.versioncodeAPK = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG,"GET VERSION APK : "+e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Save Value for Deprecate Version*/
        SessionManager
                .get(getApplicationContext())
                .saveDeprecat("Title","Desc","BTN","https://google.com");
        DeprecatedVersion(2);


    }

    private boolean hasInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected())
        {
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected())
        {
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected())
        {
            return true;
        }
        return false;
    }



    private void writeToMyFile(String json) throws IOException {
        File file = new File(getApplicationContext().getFilesDir(), "ahmad.json");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openFileOutput("ahmad.json", Context.MODE_PRIVATE);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();
            Log.d(TAG, "writeToMyFile OK > " + file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void DeprecatedVersion(int DeprecatedVersion){
        if (value.versioncodeAPK < DeprecatedVersion){
            final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
            /*Title*/
            builderSingle.setTitle(SessionManager
                    .get(getApplicationContext())
                    .getDeprecat()
                    .get(SessionManager.pref_deprecatTitle));
            /*Message*/
            builderSingle.setMessage(SessionManager
                    .get(getApplicationContext())
                    .getDeprecat()
                    .get(SessionManager.pref_deprecatDesc));
            /*Button*/
            builderSingle.setPositiveButton(SessionManager
                    .get(getApplicationContext())
                    .getDeprecat()
                    .get(SessionManager.pref_deprecatBtn),
            /*Open Url*/
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent openURL = new Intent ( Intent.ACTION_VIEW );
                    openURL.setData ( Uri.parse ( SessionManager
                            .get(getApplicationContext())
                            .getDeprecat()
                            .get(SessionManager.pref_deprecatURL) ) );
                    startActivity ( openURL );
                    finish();
                }
            });
            builderSingle.setCancelable(false);

            builderSingle.show();
        }else {

        }
    }

    private void UpdateVersion(int UpdateVersion){
        if (value.versioncodeAPK < UpdateVersion){

        }
        else {
            GetToken();
        }
    }


    private void CheckAddUser(){
        /*if (){
            GetToken();
        }else {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }*/
    }
    private void GetToken() {
        StringRequest getToken = new StringRequest(Request.Method.POST, Value.token, new Response.Listener<String>(){
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
                        Log.i(TAG,token);
                        AddUser(token);
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(TAG,msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"GET TOKEN > "+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"GET TOKEN > "+error);
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("appkey", Value.appkey);
                return headers;
            }

        };AppContoroler.getInstance().addToRequestQueue(getToken);
    }
    private void AddUser(final String token){
        final SharedPreferences pf_value = getSharedPreferences("pf_value", MODE_PRIVATE);
        final SharedPreferences.Editor pf_editorvalue = pf_value.edit();
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
        StringRequest post_user_add = new StringRequest(Request.Method.POST, Value.user_add, new Response.Listener<String>(){
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
                        pf_editorvalue.putString("v_usercode",usercode);
                        pf_editorvalue.putString("v_zoneid",zoneid);
                        pf_editorvalue.putString("v_apikey",apikey);
                        pf_editorvalue.apply();
                        Log.i(TAG,"USER ADED (Auto)"
                                +"\n usercode: "+usercode
                                +"\n zoneid: "+zoneid
                                +"\n apikey: "+apikey);
                        Log.d(TAG,"LOAD FROM > SAVE VALUE "
                                +"\n usercode: "+pf_value.getString("v_usercode",null)
                                +"\n zoneid: "+pf_value.getString("v_zoneid",null)
                                +"\n apikey: "+pf_value.getString("v_apikey",null));
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(TAG,msg_object.getString("text"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"ADD USER > "+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"ADD USER > "+error);
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
                posting.put("version", value.versionAPK );
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
