package com.ermile.salamquran.online.enter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.R;
import com.ermile.salamquran.network.AppContoroler;
import com.ermile.salamquran.online.Main_online;
import com.ermile.salamquran.saveData.SessionManager;
import com.ermile.salamquran.saveData.Value;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Enter extends AppCompatActivity {
    private static String TAG = "Enter";

    public String apikey;
    public String token;
    public String mobile;
    public String verifycode;

    TextView tvTitleNumber,
             tvTitleVerify,tvNumberVerify,tvTitleResend,tvResndVerify;

    EditText ev1,ev2,ev3,ev4,ev5,
             edtNumber;
    Button btnNumber;

    LinearLayout boxNumber,boxVerify,boxResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        apikey=SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_apikey);

        /*Number Phone*/
        /*Find Id*/
        boxNumber=findViewById(R.id.boxNumberPhone);
        tvTitleNumber=findViewById(R.id.tvTitleNumber);
        edtNumber=findViewById(R.id.edtNumberPhone);
        btnNumber=findViewById(R.id.btnNumberPhone);
        tvResndVerify=findViewById(R.id.tvResndVerify);


        boxNumber.setVisibility(View.VISIBLE);
        edtNumberMethod(edtNumber);


        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTokenNumber();
            }
        });

        tvResndVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTokenNumber();
                boxResend.setVisibility(View.GONE);
            }
        });





        /*Verify*/
        /*Find Id*/
        boxVerify=findViewById(R.id.boxVerify);
        tvTitleVerify=findViewById(R.id.tvTitleVerify);
        tvNumberVerify=findViewById(R.id.tvNumberVerify);
        ev1=findViewById(R.id.edt_verify1);
        ev2=findViewById(R.id.edt_verify2);
        ev3=findViewById(R.id.edt_verify3);
        ev4=findViewById(R.id.edt_verify4);
        ev5=findViewById(R.id.edt_verify5);
        boxResend=findViewById(R.id.boxResendVerify);
        tvTitleResend=findViewById(R.id.tvTitleResend);
        tvResndVerify=findViewById(R.id.tvResndVerify);

        /*Method EditText Verify*/
        edt1(ev1,ev2);
        edt2(ev2,ev3,ev1);
        edt3(ev3,ev4,ev2);
        edt4(ev4,ev5,ev3);
        edt5(ev5,ev4,ev1,ev2,ev3);





    }


    private void getTokenNumber(){
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
                        token = result.getString("token");
                        mobile = edtNumber.getText().toString().replace("+","");
                        Log.i(TAG,"token: "+token+" | phone: "+mobile+" | apikey: "+apikey);
                        getVerifyCode(token,mobile,apikey);

                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(TAG,msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"GET TokenNumber >JSONException "+e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"GET TokenNumber >onErrorResponse  "+error);
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

        };
        AppContoroler.getInstance().addToRequestQueue(getToken);
    }
    private void getVerifyCode(final String tokens , final String phoneNumber, final String apiKey){
        StringRequest getVerify = new StringRequest(Request.Method.POST, Value.enter, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject,result;
                JSONArray msg;
                boolean ok_getVerify;
                try {
                    mainObject = new JSONObject(response);
                    ok_getVerify = mainObject.getBoolean("ok");
                    if (ok_getVerify){
                        boxNumber.setVisibility(View.GONE);
                        boxVerify.setVisibility(View.VISIBLE);
                        tvNumberVerify.setText(mobile);
                        ev1.requestFocus();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boxResend.setVisibility(View.VISIBLE);
                            }
                        }, 120000);

                        Log.i(TAG,"");

                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(TAG,msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"GET VerifyCode >JSONException "+e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                writeNumberAfter120sec();
                Log.e(TAG,"GET VerifyCode >onErrorResponse  "+error);
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("token", token);
                headers.put("apikey", apikey);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> posting = new HashMap<>();
                posting.put("mobile", mobile);
                return posting;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getVerify);

    }

    private void getTokenVerify(){
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
                        token = result.getString("token");
                        verifycode = ev1.getText().toString()+
                                        ev2.getText().toString()+
                                        ev3.getText().toString()+
                                        ev4.getText().toString()+
                                        ev5.getText().toString();

                        Log.i(TAG,"token: "+token+" | phone: "+mobile+" | apikey: "+apikey +" | Write Verify User: "+verifycode);
                        vrifyCode(token,apikey,mobile,verifycode);

                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(TAG,msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"GET TokenVerify >JSONException "+e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"GET TokenVerify >onErrorResponse  "+error);
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

        };
        AppContoroler.getInstance().addToRequestQueue(getToken);
    }
    private void vrifyCode(final String tokens, final String apikeys, final String mobil, final String verifyCode){
        StringRequest getVerify = new StringRequest(Request.Method.POST, Value.verify, new Response.Listener<String>(){
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

                        String usercode =SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_usercode);
                        String zoneid = SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_zoneid);
                        SessionManager.get(getApplicationContext()).saveUser(usercode,zoneid,apikeyNew,mobile);

                        Log.d(TAG, "vrifyCode > apikeyNew: "+apikeyNew);
                        startActivity(new Intent(getApplicationContext(), Main_online.class));

                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(TAG,msg_object.getString("text"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"vrifyCode >JSONException "+e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"vrifyCode >onErrorResponse  "+error);
                codeVerifyNotValid();
            }
        })
                // Send Headers
        {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("token", token);
                headers.put("apikey", apikey);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> posting = new HashMap<>();
                posting.put("mobile", mobile);
                posting.put("verifycode", verifycode);
                return posting;
            }

        };
        AppContoroler.getInstance().addToRequestQueue(getVerify);
    }








    private void edtNumberMethod(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (editText.length() >=7){
                    btnNumber.setVisibility(View.VISIBLE);
                }else {
                    btnNumber.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void edt1(final EditText edt1 , final EditText edt2 ){

        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    edt2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void edt2(final EditText edt2 , final EditText edt3, final EditText edt1 ){

        edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    edt3.requestFocus();
                }else {
                    edt1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }
    private void edt3(final EditText edt3 , final EditText edt4, final EditText edt2 ){

        edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    edt4.requestFocus();
                }else {
                    edt2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void edt4(final EditText edt4 , final EditText edt5, final EditText edt3 ){

        edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    edt5.requestFocus();
                }else {
                    edt3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void edt5(final EditText edt5 , final EditText edt4 , final EditText edt1, final EditText edt2, final EditText edt3){

        edt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final int edtLength = edt1.length()+edt2.length()+edt3.length()+edt4.length()+edt5.length();
                if (charSequence.length() == 1){
                    if (edtLength == 5){
                        getTokenVerify();
                    }else {
                        edt1.getText().clear();
                        edt2.getText().clear();
                        edt3.getText().clear();
                        edt4.getText().clear();
                        edt5.getText().clear();
                        edt1.requestFocus();
                        Toast.makeText(Enter.this, "Pleas write Code "+edtLength, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    edt4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
    private void writeNumberAfter120sec(){
        btnNumber.setVisibility(View.GONE);
        final Snackbar snackbar = Snackbar.make(boxNumber, "", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setDuration(12000);
        new CountDownTimer(12000, 1000) {

            public void onTick(long millisUntilFinished) {
                snackbar.setText("Try Again after: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                btnNumber.setVisibility(View.VISIBLE);
                edtNumber.getText().clear();
                edtNumber.requestFocus();
            }

        }.start();
        snackbar.show();
    }
    private void codeVerifyNotValid(){
        btnNumber.setVisibility(View.GONE);
        final Snackbar snackbar = Snackbar.make(boxNumber, "Code Not Valid", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setDuration(3000);
        ev1.getText().clear();
        ev2.getText().clear();
        ev3.getText().clear();
        ev4.getText().clear();
        ev5.getText().clear();
        ev1.requestFocus();
        snackbar.show();
    }
}


