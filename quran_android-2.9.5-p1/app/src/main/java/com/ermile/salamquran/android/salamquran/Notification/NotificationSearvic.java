package com.ermile.salamquran.android.salamquran.Notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Splash;
import com.ermile.salamquran.android.salamquran.Utility.TempLoginUtil;
import com.ermile.salamquran.android.salamquran.tag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.ermile.salamquran.android.salamquran.Notification.Attribuites.FCM_ACTION_CLICK_NOTIFICATION;

@SuppressLint("LogNotTimber")
public class NotificationSearvic extends Service {

  public static final String CHANNEL_ID = "ForegroundServiceChannel";
  NotificationCompat.Builder builder ;
  NotificationManagerCompat notificationManager ;
  /*Handler 60sec*/
  boolean powerServic = false;
  Handler handler = new Handler();
  Runnable runnable = new Runnable() {
    @Override
    public void run() {
      if (powerServic){
        new TempLoginUtil(getApplicationContext());
        new NotificationApi(getApplicationContext(), resultArray -> Notif_is(resultArray));
        Log.d(tag.service_notification, "------------------------------------ runnable");
        handler.postDelayed(runnable, 30000);
      }
    }
  };

  @Override
  public IBinder onBind(Intent intent) {
    Log.d(tag.service_notification, "onBind"+intent);
    return null;
  }
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(tag.service_notification, "onStartCommand BEFORE \n powerServic = "+powerServic);
    createNotificationChannel();
    if (!powerServic){
      Log.d(tag.service_notification, "------------------------------------ ------------------------------------onStartCommand  powerServic = "+powerServic);
      powerServic = true;
      handler.postDelayed(runnable, 0);
    }
    return START_STICKY;
  }
  @Override
  public void onDestroy() {
    Log.d(tag.service_notification, "onDestroy");
    super.onDestroy();
    Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();
  }

  private void createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel serviceChannel = new NotificationChannel(
          CHANNEL_ID,
          "Foreground Service Channel",
          NotificationManager.IMPORTANCE_DEFAULT
      );
      NotificationManager manager = getSystemService(NotificationManager.class);
      manager.createNotificationChannel(serviceChannel);
    }
  }

  // get Notification and run for user > Yes Notif is ..
  public void Notif_is(final String resultArray){
    try {
      JSONArray result = new JSONArray(resultArray);
      for (int j = 0; j < result.length(); j++) {
        JSONObject jsonObject = result.getJSONObject(j);
        String title = "";
        String excerpt = "";
        String desc = "";
        String info = getApplicationContext().getString(R.string.app_name);

        if (!jsonObject.isNull("title")){
          title = jsonObject.getString("title");
        }
        if (!jsonObject.isNull("excerpt")){
          excerpt = jsonObject.getString("excerpt");
        }

        if (!jsonObject.isNull("text")){
          desc = jsonObject.getString("text");
        }
        else if (!jsonObject.isNull("excerpt")){
          desc = excerpt;
        }

        send_Notif(title,excerpt,desc,info,j);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

  private void send_Notif(String title,String excerpt,String desc,String info,int id){

    Intent intent = new Intent(this, Splash.class); //Open activity if clicked on notification
    PendingIntent pendingIntent;

    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.setAction(FCM_ACTION_CLICK_NOTIFICATION);
    pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

    int randomNumber = new Random().nextInt(976431 ) + 20;
    builder = new NotificationCompat.Builder(this,CHANNEL_ID);

    Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //Notification alert sound

    notificationManager = NotificationManagerCompat.from(getApplicationContext());
    builder
        .setContentTitle(title)
        .setContentText(excerpt)
        .setStyle(new NotificationCompat.BigTextStyle().bigText(desc))
        .setAutoCancel(true)
        .setLights(Color.BLUE,1,1)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent)
        .setContentInfo(info)
        .setSmallIcon(R.drawable.ic_notification)
        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.salamquran))
        .setColor(ContextCompat.getColor(this, R.color.colorPrimary));
    notificationManager.notify(100+randomNumber+id, builder.build());
  }
}
