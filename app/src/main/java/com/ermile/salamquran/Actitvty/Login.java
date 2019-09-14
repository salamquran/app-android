package com.ermile.salamquran.Actitvty;

import android.content.Intent;
import android.graphics.Color;
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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Function.api.Token;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "Enter";

    TextView tvTitleNumber,tvTitleVerify,tvNumberVerify,tvTitleResend,tvResndVerify;
    EditText edtVerify_1, edtVerify_2, edtVerify_3, edtVerify_4, edtVerify_5, edtWriteNumber;
    Button btnNumber;
    LinearLayout boxNumber,boxVerify,boxResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        EditTextSetMethode(edtVerify_1, edtVerify_2, edtVerify_3, edtVerify_4, edtVerify_5, edtWriteNumber);

        boxNumber.setVisibility(View.VISIBLE);
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step1_VerifyNumber();
            }
        });
        tvResndVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step1_VerifyNumber();
                boxResend.setVisibility(View.GONE);
            }
        });

    }


    private String getNumberPhone(){
        return edtWriteNumber.getText().toString().replace("+","");
    }
    private String getApiKey(){
        return SaveManager.get(this).getstring_appINFO().get(SaveManager.apiKey);
    }
    private String getUserVerfycationCode(){
        return edtVerify_1.getText().toString()
                + edtVerify_2.getText().toString()
                + edtVerify_3.getText().toString()
                + edtVerify_4.getText().toString()
                + edtVerify_5.getText().toString();
    }

    /** Connection To Server*/
    /*Writ Number Phone*/
    private void step1_VerifyNumber(){
        Token.GetToken(new Token.TokenListener() {
            @Override
            public void onTokenRecieved(final String token) {
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
                                boxNumber.setVisibility(View.GONE);
                                boxVerify.setVisibility(View.VISIBLE);
                                tvNumberVerify.setText(getNumberPhone());
                                edtVerify_1.requestFocus();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        boxResend.setVisibility(View.VISIBLE);
                                    }
                                }, 120000);
                            }else {
                                msg = mainObject.getJSONArray("msg");
                                for (int i = 0 ; i<= msg.length();i++){
                                    JSONObject msg_object = msg.getJSONObject(i);
                                    Log.e(tag.error,"VerifyNumberFetcher HasNet: "+msg_object.getString("text"));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(tag.error,"VerifyNumberFetcher >JSONException "+e);
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(tag.error,"VerifyNumberFetcher > onErrorResponse  "+error);
                    }
                })
                        // Send Headers
                {
                    @Override
                    public Map<String, String> getHeaders()  {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("token", token);
                        headers.put("apikey", getApiKey());
                        return headers;
                    }
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> posting = new HashMap<>();
                        posting.put("mobile", getNumberPhone());
                        return posting;
                    }

                };
                AppContoroler.getInstance().addToRequestQueue(getVerify);
            }

            @Override
            public void onTokenFailed(String error) {

            }
        });
    }

    /*Writ Verify Code*/
    private void step2_VerifyCodeSMS(){
        Token.GetToken(new Token.TokenListener() {
            @Override
            public void onTokenRecieved(final String token) {
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
                                String usercode  =  SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.userCode);
                                String zoneid    =  SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.zoneID);

                                SaveManager.get(getApplicationContext()).change_infoLOGIN(apikeyNew,usercode,zoneid);
                                nexActivity();

                            }else {
                                msg = mainObject.getJSONArray("msg");
                                for (int i = 0 ; i<= msg.length();i++){
                                    JSONObject msg_object = msg.getJSONObject(i);
                                    Log.e(tag.error,"VerifyCodeFethcer: "+ msg_object.getString("text"));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(tag.error,"VerifyCodeFethcer >JSONException "+e);
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(tag.error,"VerifyCodeFethcer > onErrorResponse  "+error);
                    }
                })
                        // Send Headers
                {
                    @Override
                    public Map<String, String> getHeaders()  {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("token", token);
                        headers.put("apikey", getApiKey());
                        return headers;
                    }
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> posting = new HashMap<>();
                        posting.put("mobile", getNumberPhone());
                        posting.put("verifycode", getUserVerfycationCode());
                        return posting;
                    }

                };
                AppContoroler.getInstance().addToRequestQueue(getVerify);
            }

            @Override
            public void onTokenFailed(String error) {

            }
        });
    }

    private void nexActivity() {
        finish();
        startActivity(new Intent(this,Main.class));
    }

    /** Edit Text Method*/
    private void EditTextSetMethode(final EditText Verify_1 , final EditText Verify_2 , final EditText Verify_3, final EditText Verify_4, final EditText Verify_5, final EditText Number){
        Number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (Number.length() >=7){
                    btnNumber.setVisibility(View.VISIBLE);
                }else {
                    btnNumber.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Verify_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(tag.error, "beforeTextChanged: "+i+"|"+i1+"|"+i2);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(tag.error, "onTextChanged: "+i+"|"+i1+"|"+i2);
                if (charSequence.length() == 1){
                    Verify_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(tag.error, "afterTextChanged: "+editable);

            }
        });

        Verify_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    Verify_3.requestFocus();
                }else {
                    Verify_1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        Verify_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    Verify_4.requestFocus();
                }else {
                    Verify_2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Verify_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    Verify_5.requestFocus();
                }else {
                    Verify_3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Verify_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final int edtLength = getUserVerfycationCode().length();
                if (charSequence.length() == 1){
                    if (edtLength == 5){
                        step2_VerifyCodeSMS();
                    }else {
                        Verify_1.getText().clear();
                        Verify_2.getText().clear();
                        Verify_3.getText().clear();
                        Verify_4.getText().clear();
                        Verify_5.getText().clear();
                        Verify_1.requestFocus();
                        Toast.makeText(Login.this, "Pleas write Code "+edtLength, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Verify_4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    /** Static Method*/
    /*Find Id*/
    private void findViewById(){
        /*Number Phone*/
        boxNumber=findViewById(R.id.boxNumberPhone_login);
        tvTitleNumber=findViewById(R.id.textViewTitleNumber_login);
        edtWriteNumber =findViewById(R.id.edtNumberPhone_login);
        btnNumber=findViewById(R.id.btnNumberPhone_login);
        tvResndVerify=findViewById(R.id.tvResndVerify_login);
        /*Verify*/
        boxVerify=findViewById(R.id.boxVerify_login);
        tvTitleVerify=findViewById(R.id.textViewTitleVerify_login);
        tvNumberVerify=findViewById(R.id.textViewNumberVerify_login);
        edtVerify_1 =findViewById(R.id.edt_verify1_login);
        edtVerify_2 =findViewById(R.id.edt_verify2_login);
        edtVerify_3 =findViewById(R.id.edt_verify3_login);
        edtVerify_4 =findViewById(R.id.edt_verify4_login);
        edtVerify_5 =findViewById(R.id.edt_verify5_login);
        boxResend=findViewById(R.id.boxResendVerify_login);
        tvTitleResend=findViewById(R.id.tvTitleResend_login);
        tvResndVerify=findViewById(R.id.tvResndVerify_login);
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
                edtWriteNumber.getText().clear();
                edtWriteNumber.requestFocus();
            }

        }.start();
        snackbar.show();
    }
    private void codeVerifyNotValid(){
        btnNumber.setVisibility(View.GONE);
        final Snackbar snackbar = Snackbar.make(boxNumber, "Code Not Valid", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setDuration(3000);
        edtVerify_1.getText().clear();
        edtVerify_2.getText().clear();
        edtVerify_3.getText().clear();
        edtVerify_4.getText().clear();
        edtVerify_5.getText().clear();
        edtVerify_1.requestFocus();
        snackbar.show();
    }

    @Override
    public void onClick(View view) {
        Log.d(tag.error, "onFocusChange: "+view.getId());
    }
}
