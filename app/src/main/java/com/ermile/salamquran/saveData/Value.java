package com.ermile.salamquran.saveData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Value extends PreferenceActivity {

    public String local = "https://jibres.ir/api/v6/app";

    public boolean firstOpen = false;
    public String appLanguage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pf_value = PreferenceManager.getDefaultSharedPreferences(this);

        /*First open*/
        Boolean v_firstOpen = pf_value.getBoolean("v_firstOpen",false);

        /*App Language*/
        String v_appLanguage = pf_value.getString("v_appLanguage", "en");



        Boolean v_b = pf_value.getBoolean("v_b",false);
        String v_s = pf_value.getString("v_s", null);
    }
}

