package com.ermile.salamquran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.network.AppContoroler;
import com.ermile.salamquran.saveData.Value;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Locale;

public class Splash extends AppCompatActivity {
    /*Get language device*/
    private String language_device = Locale.getDefault().getLanguage();

    /*Value*/
    Value value = new Value();

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ChangeLanguage();
    }




    private void JSON_APP(){
        StringRequest get_local = new StringRequest(Request.Method.GET, value.local, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                boolean ok =false;
                JSONObject result = null;
                JSONObject lang_list = null;

                try {
                    JSONObject mainObject = new JSONObject(response);
                    if(!mainObject.isNull("ok")) ok = mainObject.getBoolean("ok");

                    if (ok){
                        if (!mainObject.isNull("result")) result = mainObject.getJSONObject("result");
                        // Get Language List
                        if (!mainObject.isNull("lang_list")) lang_list = result.getJSONObject("lang_list");
                        Iterator<String> keys = lang_list.keys();
                        for (Object key :  keys.keySet()) {
                            //based on you key types
                            String keyStr = (String)key;
                            Object keyvalue = jsonObj.get(keyStr);

                            //Print key and value
                            System.out.println("key: "+ keyStr + " value: " + keyvalue);

                            //for nested objects iteration if required
                            if (keyvalue instanceof JSONObject)
                                printJsonObject((JSONObject)keyvalue);
                        }



                        String token = result.getString("token");

                        editor.putString("token", token);
                        editor.apply();
                        add_user();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppContoroler.getInstance().addToRequestQueue(get_local);
    }


    /*Change Language > medal list*/
    private void ChangeLanguage(){
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.logo_salamquran);
        builderSingle.setTitle("Select One Name:-");





        arrayAdapter.add("Hardik");
        arrayAdapter.add("Archit");
        arrayAdapter.add("Jignesh");
        arrayAdapter.add("Umang");
        arrayAdapter.add("Gatti");


        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);

                final AlertDialog.Builder builderInner = new AlertDialog.Builder(getApplicationContext());
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        Toast.makeText(Splash.this, arrayAdapter.getCount(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builderSingle.show();
    }
}
