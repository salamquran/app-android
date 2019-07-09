package com.ermile.salamquran.online;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ermile.salamquran.R;

public class Main_online extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_online);
        String Language = "fa";
        Language(Language);

    }


    private void Language(String deviceLanguage){
        switch (deviceLanguage){
            case "fa":

                break;
            case "ar":
                break;
            default:
                break;
        }
    }

    private String Apply(String name){



        return null;
    }
}
