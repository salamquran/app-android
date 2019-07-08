package com.ermile.salamquran.saveData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Value extends PreferenceActivity {

    public static String local = "https://jibres.ir/api/v6/app";
    public static String site = "https://jibres.ir";
    public static String apiV6 = "https://jibres.ir/api/v6/";
    public static String token = apiV6+"token";
    public static String user_add = apiV6+"android/user/add";
    public static String appkey = null;
    public String versionAPK = null;
    public int versioncodeAPK = 1;

    public boolean firstOpen = false;
    public String appLanguage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pf_value = PreferenceManager.getDefaultSharedPreferences(this);
        String v_json = pf_value.getString("v_json", null);

        /*First open*/
        Boolean v_firstOpen = pf_value.getBoolean("v_firstOpen",false);

        /*App Language*/
        String v_appLanguage = pf_value.getString("v_appLanguage", "en");


        /*Add User Auto*/
        String v_usercode = pf_value.getString("v_usercode", null);
        String v_zoneid = pf_value.getString("v_zoneid", null);
        String v_apikey = pf_value.getString("v_apikey", null);


        Boolean v_b = pf_value.getBoolean("v_b",false);
        String v_s = pf_value.getString("v_s", null);
    }


}

