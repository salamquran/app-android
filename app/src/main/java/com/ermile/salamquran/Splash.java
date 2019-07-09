package com.ermile.salamquran;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.network.AppContoroler;
import com.ermile.salamquran.offline.Main_offline;
import com.ermile.salamquran.online.Main_online;
import com.ermile.salamquran.saveData.SessionManager;
import com.ermile.salamquran.saveData.Value;
import com.ermile.salamquran.statice.Intro;
import com.ermile.salamquran.statice.Language;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Splash extends AppCompatActivity { private static String TAG = "Splash";
    /*Get language device*/
    private String language_device = Locale.getDefault().getLanguage();
    /*Value*/
    Value value = new Value();
    /*Object's of activity_splash.xml*/
    LinearLayout linearLayout;
    ImageView logo;
    TextView title,desc,titleProgress;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String AppLanguage = SessionManager.get(getApplicationContext()).getAppLanguage().get(SessionManager.pref_appLanguage);
        Log.d(TAG, "AppLanguage: "+AppLanguage);

        if (AppLanguage == null){
            carateFirstJsonFile();
            setFirstLanguage();
        }else {
            getSettingJson();
        }
    }


    /** Set First Language Auto from Device*/
    /*Set First Language*/
    private void setFirstLanguage(){
        switch (language_device){
            case "fa":
                SessionManager.get(getApplicationContext()).saveAppLanguage(language_device,language_device);
                Log.d(TAG, "onCreate: set language > "+language_device);
                getSettingJson();
                break;
            case "ar":
                SessionManager.get(getApplicationContext()).saveAppLanguage(language_device,language_device);
                Log.d(TAG, "onCreate: set language > "+language_device);
                getSettingJson();
                break;
            default:
                SessionManager.get(getApplicationContext()).saveAppLanguage("en",null);
                getSettingJson();
                Log.d(TAG, "onCreate: set language > "+"en");
                break;
        }
    }
    /*Set Language by User*/
    private void setLanguageByUser(){
        String App= SessionManager.get(getApplicationContext()).getAppLanguage().get(SessionManager.pref_app);
        if (App ==null){
            guide_Language();
            Log.d(TAG, "setLanguageByUser");
        }else{
            deprecatedVersion();
            Log.d(TAG, "LanguageByUser Is Set");
        }
    }


    /** Get Setting Json Online(if connected) & Offline(if null)*/
    private void getSettingJson(){
        String AppLanguage = SessionManager.get(getApplicationContext()).getAppLanguage().get(SessionManager.pref_appLanguage);
        if (hasInternetConnection()){
            /*Get Setting From Url*/
            StringRequest get_local = new StringRequest(Request.Method.GET, Value.local, new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d(TAG, "Connection to Server and write");
                        writeToMyFile(response);
                        setLanguageByUser();
                    }catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Not Write online Json: +"+e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Not Write online Json (onErrorResponse) : "+error);
                }
            });AppContoroler.getInstance().addToRequestQueue(get_local);
        }
        else {
            try {
                if (readFromMyFile(Value.jsonFileName).equals("")) {
                    writeToMyFile(loadJSONFromAsset(AppLanguage));
                    Log.d(TAG, "Write offline Json to local.json");
                    setLanguageByUser();
                }else {
                    setLanguageByUser();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Not Write offline Json: "+e);
            }
        }
    }


    /** Check Update Or Deprecated Version*/
    /*Check Deprecated Version*/
    private void deprecatedVersion(){
        try {
            JSONObject respone = new JSONObject(readFromMyFile(Value.jsonFileName));
            JSONObject result = respone.getJSONObject("result");
            JSONObject url = result.getJSONObject("url");
            JSONObject version = result.getJSONObject("version");
            /*Url For Update*/
            final String urlUpdate = url.getString("update");
            /*Deprecate Value*/
            String deprecatedVersion = version.getString("deprecated").replace(".","");
            int Depver = Integer.valueOf(deprecatedVersion);
            String deprecated_title = version.getString("deprecated_title");
            String deprecated_desc = version.getString("deprecated_desc");
            String deprecated_btnTitle = "Update Now!";
            /*Update Value*/
            String lastVersion = version.getString("last").replace(".","");
            int Updver = Integer.valueOf(lastVersion);
            String update_title = version.getString("update_title");
            String update_desc = version.getString("update_desc");
            if (value.versioncodeAPK < Depver){
                final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
                /*Title*/
                builderSingle.setTitle(deprecated_title);
                /*Message*/
                builderSingle.setMessage(deprecated_desc);
                /*Button*/
                builderSingle.setPositiveButton(deprecated_btnTitle,
                        /*Open Url*/
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent openURL = new Intent ( Intent.ACTION_VIEW );
                                openURL.setData (Uri.parse( urlUpdate ));
                                startActivity ( openURL );
                                finish();
                            }
                        });
                builderSingle.setCancelable(false);
                builderSingle.show();
                Log.d(TAG, "Version is Deprecated (Show Dialog)");
            }else {
                updateVersion(Updver,update_title,update_desc,urlUpdate);
                Log.d(TAG, "Version Not Deprecated, Check Update Version");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Version Deprecated: "+e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Version Deprecated: "+e);
        }
    }
    /*Check Update Version*/
    private void updateVersion(int UpdateVersion, String title, String desc, String url){
        if (value.versioncodeAPK < UpdateVersion){
            SessionManager.get(getApplicationContext()).saveUpdate(title,desc,url);
            Log.d(TAG, "Update Version (Save Value)");
        }
        else {
            guide();
        }
    }


    /** Guid for Check and Go (Add User)&(Go to Activity)*/
    private void guide(){
        if (hasInternetConnection()){
            if (!userIsAded()){
                getToken();
            }else {
                guide_online();
            }
        }else {
            if (!userIsAded()){
                guide_intro();
            }else {
                guide_offline();
            }
        }
    }
    private void guide_intro(){finish();startActivity(new Intent(this, Intro.class));}
    private void guide_online(){finish();startActivity(new Intent(this, Main_online.class));}
    private void guide_offline(){finish();startActivity(new Intent(this,Main_offline.class));}
    private void guide_Language(){finish();startActivity(new Intent(this, Language.class));}


    /** Add User Auto*/
    /*check user is add our not*/
    private boolean userIsAded(){
        String usercode = SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_usercode);
        String zoneid = SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_zoneid);
        String apikey = SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_apikey);
        if (usercode == null){
            Log.e(TAG, "UserAded: usercode is Null");
            return false;
        }else if (zoneid == null){
            Log.e(TAG, "UserAded: zoneid is Null");
            return false;
        }else if (apikey == null){
            Log.e(TAG, "UserAded: apikey is Null");
            return false;
        }
        return true;
    }
    /*Get Token*/
    private void getToken() {
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
                        addUser(token);
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
                errorNet();
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
    /*Add User By Token (Auto)*/
    private void addUser(final String token){
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
                        SessionManager.get(getApplicationContext()).saveUser(usercode,zoneid,apikey);
                        Log.i(TAG,"USER ADED (Auto)"
                                +"\n usercode: "+usercode
                                +"\n zoneid: "+zoneid
                                +"\n apikey: "+apikey);
                        guide_intro();
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
                errorNet();
                Log.e(TAG,"ADD USER > "+error + "| Start Guide");
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


    /** File Json Read & Write*/
    /*Crate json file (local.json)*/
    private void carateFirstJsonFile(){
        try {
            writeToMyFile("");
            Log.d(TAG, "Json File Crated");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Json File Not Crated");
        }
    }
    /*Write To Json (local.json)*/
    private void writeToMyFile(String json) throws IOException {
        File file = new File(getApplicationContext().getFilesDir(), Value.jsonFileName+".json");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(Value.jsonFileName+".json", Context.MODE_PRIVATE);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();
            Log.d(TAG, "writeToMyFile OK > " + file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*Read Json File From Assets Folder (APK)*/
    public String loadJSONFromAsset(String langName) {
        try {
            InputStream file= this.getAssets().open(langName+"_offline.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
            }
            bufferedReader.close();
            return text.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /*Read Json File From Device Folder (Internal)*/
    private String readFromMyFile(String filename) throws IOException {
        File file = new File(getApplicationContext().getFilesDir(), filename+".json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        return text.toString();
    }



    /** Static Method*/
    /*Check Internet Connection*/
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
    /*Show Snack Bar In Error Json*/
    private void errorNet(){
        linearLayout = findViewById(R.id.lineP_splash);
//        txt_load.setText(title_snackbar);
        Snackbar snackbar = Snackbar.make(linearLayout, "Error Net", Snackbar.LENGTH_INDEFINITE)
                .setAction("Refresh", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(getIntent());
                    }
                });
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setDuration(999999999);
        snackbar.show();
    }


    /** Oder Method (No Used)*/
    private void alertDialog(String title, String desc, String btnTitle, boolean Cancelable){
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        /*Title*/
        builderSingle.setTitle(title);
        /*Message*/
        builderSingle.setMessage(desc);
        /*Button*/
        builderSingle.setPositiveButton(btnTitle,
                /*Open Url*/
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builderSingle.setCancelable(Cancelable);
        builderSingle.show();
    }
}
