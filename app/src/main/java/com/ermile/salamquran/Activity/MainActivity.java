package com.ermile.salamquran.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ermile.salamquran.Fragment.ListQuran;
import com.ermile.salamquran.R;

public class MainActivity extends AppCompatActivity {

    Fragment fragment =  null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new ListQuran();
        setFragment();

    }

    void setFragment(){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
