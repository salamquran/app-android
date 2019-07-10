package com.ermile.salamquran.online.enter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ermile.salamquran.R;

public class Enter extends AppCompatActivity {

    EditText ev1,ev2,ev3,ev4,ev5,
             number;
    Button btnVrify,btnNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        ev1=findViewById(R.id.edt_verify1);
        ev2=findViewById(R.id.edt_verify2);
        ev3=findViewById(R.id.edt_verify3);
        ev4=findViewById(R.id.edt_verify4);
        ev5=findViewById(R.id.edt_verify5);


        edt_1t2(ev1,ev2);
        edt_2t3(ev2,ev3,ev1);
        edt_3t4(ev3,ev4,ev2);
        edt_4t5(ev4,ev5,ev3);
        edt_5(ev5,ev4);



    }

    private void edt_1t2(final EditText oneeditText , final EditText twoeditText ){

        oneeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    twoeditText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void edt_2t3(final EditText oneeditText , final EditText twoeditText, final EditText backEdittext ){

        oneeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    twoeditText.requestFocus();
                }else {
                    backEdittext.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }
    private void edt_3t4(final EditText oneeditText , final EditText twoeditText, final EditText backEdittext ){

        oneeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    twoeditText.requestFocus();
                }else {
                    backEdittext.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void edt_4t5(final EditText oneeditText , final EditText twoeditText, final EditText backEdittext ){

        oneeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    twoeditText.requestFocus();
                }else {
                    backEdittext.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void edt_5(final EditText oneeditText ,  final EditText backEdittext ){

        oneeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1){
                    Toast.makeText(Enter.this, "Check Vrify", Toast.LENGTH_SHORT).show();
                }else {
                    backEdittext.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}

