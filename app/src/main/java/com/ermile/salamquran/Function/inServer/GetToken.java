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
            final String Localid ;



        }
        return null;
    }
}