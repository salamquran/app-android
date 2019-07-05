package com.ermile.salamquran.saveData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Value extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pf_value = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean v_b = pf_value.getBoolean("v_b",true);
        String v_s = pf_value.getString("v_s", null);

    }
}

