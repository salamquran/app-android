package com.ermile.salamquran.android.salamquran;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class CheckNetworkBroadCast extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

    if(isConnected){
      Toast.makeText(context, "اینترنت وصل است", Toast.LENGTH_SHORT).show();
    }else{
      Toast.makeText(context, "اینترنت قطع است", Toast.LENGTH_SHORT).show();
    }
  }
}
