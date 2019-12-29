package com.ermile.salamquran.android.salamquran;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ermile.salamquran.android.salamquran.Notification.NotificationSearvic;
import com.ermile.salamquran.android.salamquran.Utility.TempLoginUtil;
import com.ermile.salamquran.android.salamquran.api.Api;

public class CheckNetworkBroadCast extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    context.startService(new Intent(context.getApplicationContext(), NotificationSearvic.class));
    new TempLoginUtil(context);
    /*Api.getAndroidDetail(context);
    Api.getLanguageList(context);
    Api.getAyaDay(context);
    Api.getPageDay(context);*/

  }
}
