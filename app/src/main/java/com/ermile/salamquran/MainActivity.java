package com.ermile.salamquran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
