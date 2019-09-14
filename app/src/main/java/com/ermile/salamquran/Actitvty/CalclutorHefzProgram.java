package com.ermile.salamquran.Actitvty;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.Network.AppContoroler;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;
import com.ermile.salamquran.Static.value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalclutorHefzProgram extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calclutor_hefzprogram);
        editText = findViewById(R.id.edt_CalcultorHefz);
        Button button = findViewById(R.id.btn_CalcultorHefz);
        button.setText(value.btn_CalclutorHefzProgram);
        final TextView textView = findViewById(R.id.textView_CalcultorHefz);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getEditText() !=null){
                    CalclutorHefzProgram.GetCalclutoHefz(getEditText(), new CalclutorHefzProgram.CalClutorHefz_Listener() {
                        @Override
                        public void result_CalclutorHefzProgram(String yers, String month) {
                            editText.setBackgroundColor(Color.GREEN);
                            if (!yers.equals("0")){
                                textView.setText("اگر روزانه "+getEditText()+" دقیقه از قرآن رو حفظ کنی در"+ yers+" سال و "+month+" ماه حافظ کل قرآن میشی" );
                            }
                            else {
                                textView.setText("اگر روزانه "+getEditText()+" دقیقه از قرآن رو حفظ کنی در"+month+" ماه حافظ کل قرآن میشی" );
                            }

                        }

                        @Override
                        public void filed_CalclutorHefzProgram(String error) {
                            textView.setText(error);

                        }
                    });
                }
                else {
                    editText.setBackgroundColor(Color.RED);
                    Toast.makeText(CalclutorHefzProgram.this, "دقیقه ایی وارد نشده!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }


    private String getEditText(){
        if (editText.getText().toString().equals("")){
            return null;
        }
        else {
            return editText.getText().toString();
        }
    }

    public static void GetCalclutoHefz(final String userTime , final CalclutorHefzProgram.CalClutorHefz_Listener listener_CalclutorHefz) {
        StringRequest request = new StringRequest(Request.Method.GET, url.calclutorHefzProgram+"?mytime="+userTime, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject mainObject,result;
                JSONArray msg;
                boolean ok;
                try {
                    mainObject = new JSONObject(response);
                    ok = mainObject.getBoolean("ok");
                    if (ok){
                        result = mainObject.getJSONObject("result");
                        if (!result.isNull("year") || !result.isNull("month")){
                            String year = String.valueOf(result.getInt("year"));
                            String month = String.valueOf(result.getInt("month"));
                            listener_CalclutorHefz.result_CalclutorHefzProgram(year,month);
                        }else {
                            Log.e(tag.error, "CalclutorHefzProgram: Year & Month is NULL \n respone: "+ response );
                            listener_CalclutorHefz.filed_CalclutorHefzProgram(value.error_CalclutorHefzProgram);
                        }
                    }else {
                        msg = mainObject.getJSONArray("msg");
                        for (int i = 0 ; i<= msg.length();i++){
                            JSONObject msg_object = msg.getJSONObject(i);
                            Log.e(tag.error, "CalclutorHefzProgram: \n msg"+ msg_object );
                            listener_CalclutorHefz.filed_CalclutorHefzProgram(value.error_CalclutorHefzProgram);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    listener_CalclutorHefz.filed_CalclutorHefzProgram(value.error_CalclutorHefzProgram);
                    Log.e(tag.error, "CalclutorHefzProgram : \nJSONException",e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                listener_CalclutorHefz.filed_CalclutorHefzProgram(value.error_CalclutorHefzProgram);
                Log.e(tag.error, "CalclutorHefzProgram : \nVolleyError",e );
            }
        });
        AppContoroler.getInstance().addToRequestQueue(request);

    }



    public interface CalClutorHefz_Listener{
        void result_CalclutorHefzProgram(String yers,String month);
        void filed_CalclutorHefzProgram(String error);
    }

}
