package com.ermile.salamquran.Function.inApp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.ermile.salamquran.Static.tag;

public class HasConnection {
    Context context;

    public Boolean HasConnection(Context context) {
        this.context = context;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected())
        {
            Log.d(tag.function, "HasConnection: "+true);
            Log.d(tag.ac_Splash, "HasConnection: "+true);
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected())
        {
            Log.d(tag.function, "HasConnection: "+true);
            Log.d(tag.ac_Splash, "HasConnection: "+true);
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected())
        {
            Log.d(tag.function, "HasConnection: "+true);
            Log.d(tag.ac_Splash, "HasConnection: "+true);
            return true;
        }
        Log.e(tag.function, "HasConnection: "+false);
        Log.e(tag.ac_Splash, "HasConnection: "+false);
        return false;
    }

}
