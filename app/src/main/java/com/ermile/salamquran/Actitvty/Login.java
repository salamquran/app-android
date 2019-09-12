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

import com.ermile.salamquran.Function.GetToken.TokenFetcher;
import com.ermile.salamquran.Function.GetToken.TokenListener;
import com.ermile.salamquran.Function.LoginByNumber.step1_VerifyNumber.Enter;
import com.ermile.salamquran.Function.LoginByNumber.step1_VerifyNumber.EnterListener;
import com.ermile.salamquran.Function.LoginByNumber.step2_VerifyCode.VerifuCodeListener;
import com.ermile.salamquran.Function.LoginByNumber.step2_VerifyCode.VerifyCodeFethcer;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;
import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {
    private static String TAG = "Enter";

    TextView tvTitleNumber,tvTitleVerify,tvNumberVerify,tvTitleResend,tvResndVerify;
    EditText ev1,ev2,ev3,ev4,ev5,edtNumber;
    Button btnNumber;
    LinearLayout boxNumber,boxVerify,boxResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        edtSetMethod();

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
        return edtNumber.getText().toString().replace("+","");
    }
    private String getApiKey(){
        return SaveManager.get(this).getstring_appINFO().get(SaveManager.apiKey);
    }
    private String getUserVerfycationCode(){
        return ev1.getText().toString()
                + ev2.getText().toString()
                + ev3.getText().toString()
                + ev4.getText().toString()
                + ev5.getText().toString();
    }

    /** Connection To Server*/
    /*Writ Number Phone*/
    private void step1_VerifyNumber(){
        TokenFetcher.GetToken(new TokenListener() {
            @Override
            public void onTokenRecieved(String token) {
                Log.d(tag.publicsh, "onTokenRecieved: Enter "+token+" | "+getNumberPhone()+" | "+getApiKey());
                Enter.sendNumberToVerify(token, getNumberPhone(), getApiKey(), new EnterListener() {
                    @Override
                    public void SendCodeToNumber() {
                        boxNumber.setVisibility(View.GONE);
                        boxVerify.setVisibility(View.VISIBLE);
                        tvNumberVerify.setText(getNumberPhone());
                        ev1.requestFocus();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boxResend.setVisibility(View.VISIBLE);
                            }
                        }, 120000);
                    }

                    @Override
                    public void ErrorSendCodeToNumber(String Error) {
                        boxResend.setVisibility(View.VISIBLE);
                        Log.e(tag.error, "ErrorSendCodeToNumber: "+Error);
                    }
                });


            }

            @Override
            public void onTokenFailed(String error) {

            }
        });
    }

    /*Writ Verify Code*/
    private void step2_VerifyCodeSMS(){

        TokenFetcher.GetToken(new TokenListener() {
            @Override
            public void onTokenRecieved(String token) {
                VerifyCodeFethcer.vrifyCode(getApplicationContext(), token, getApiKey(), getNumberPhone(), getUserVerfycationCode(), new Intent(getApplicationContext(), Main.class), new VerifuCodeListener() {
                    @Override
                    public void VerifyCode(String apikey, String zoneID, String userCode) {
                        SaveManager.get(getApplicationContext()).change_infoLOGIN(apikey,userCode,zoneID);
                    }

                    @Override
                    public void ErrorGetVerifyCode(String Error) {

                    }
                });

            }

            @Override
            public void onTokenFailed(String error) {

            }
        });
    }

    /** Edit Text Method*/
    /*EditText Set Method*/
    private void edtSetMethod(){
        /*Method EditText Number*/
        edtNumberMethod(edtNumber);
        /*Method EditText Verify*/
        edt1(ev1,ev2);
        edt2(ev2,ev3,ev1);
        edt3(ev3,ev4,ev2);
        edt4(ev4,ev5,ev3);
        edt5(ev5,ev4,ev1,ev2,ev3);
    }
    /*Number*/
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
    /*Verify*/
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
                        step2_VerifyCodeSMS();
                    }else {
                        edt1.getText().clear();
                        edt2.getText().clear();
                        edt3.getText().clear();
                        edt4.getText().clear();
                        edt5.getText().clear();
                        edt1.requestFocus();
                        Toast.makeText(Login.this, "Pleas write Code "+edtLength, Toast.LENGTH_SHORT).show();
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
    /*Find Id*/
    private void findViewById(){
        /*Number Phone*/
        boxNumber=findViewById(R.id.boxNumberPhone);
        tvTitleNumber=findViewById(R.id.tvTitleNumber);
        edtNumber=findViewById(R.id.edtNumberPhone);
        btnNumber=findViewById(R.id.btnNumberPhone);
        tvResndVerify=findViewById(R.id.tvResndVerify);
        /*Verify*/
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
