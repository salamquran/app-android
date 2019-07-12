package com.ermile.salamquran.statice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ermile.salamquran.R;

public class AboutApp extends AppCompatActivity {
    ImageView logo;
    TextView title,slogan,desc,version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);
        findViewById();







    }


    private void findViewById(){
        logo=findViewById(R.id.imgvLogo_AboutApp);
        title=findViewById(R.id.tvTitle_AboutApp);
        slogan=findViewById(R.id.tvSlogan_AboutApp);
        desc=findViewById(R.id.tvDesc_AboutApp);
        version=findViewById(R.id.tvVersion_AboutApp);
    }

}
