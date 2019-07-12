package com.ermile.salamquran.statice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ermile.salamquran.R;
import com.ermile.salamquran.saveData.SessionManager;

public class ShearApp extends AppCompatActivity {

    TextView tvUserCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shearapp);
        findViewById();

        String usercode = SessionManager.get(getApplicationContext()).getUser().get(SessionManager.pref_usercode);
        tvUserCode.setText(usercode);

    }

    private void findViewById(){
        tvUserCode=findViewById(R.id.tvUserCode_ShearApp);
    }
}
