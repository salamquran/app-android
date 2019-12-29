package com.ermile.salamquran.android.salamquran.Notification;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.salamquran.Utility.Url;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationApi {

  private Context context;
  private notifListener listener;

  public NotificationApi(Context context, notifListener listener) {
    this.context = context;
    this.listener = listener;

    smile(() -> {
      StringRequest request =
          new StringRequest(Request.Method.POST, Url.getNotif(context), response -> {
            JSONObject mainObject;
            boolean ok_getToken;
            try {
              mainObject = new JSONObject(response);
              ok_getToken = mainObject.getBoolean("ok");
              if (ok_getToken){
                JSONArray result = mainObject.getJSONArray("result");
                listener.onMassage(String.valueOf(result));
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }, Throwable::printStackTrace)
          {
            @Override
            public Map<String, String> getHeaders()  {
              HashMap<String, String> headers = new HashMap<>();
              headers.put("apikey", UserInfo.getApikey(context));
              return headers;
            }
          };
      request.setRetryPolicy( new DefaultRetryPolicy(
          5 * 1000,
          0,
          DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
      QuranApplication.getInstance().addToRequestQueue(request);
    });
  }
  public interface notifListener{
    void onMassage(String resultArray);
  }


  private void smile(smileListener listener){
    StringRequest request =
        new StringRequest(Request.Method.POST, Url.getSmile(context), response -> {
      JSONObject mainObject,result;
      JSONArray msg;
      boolean ok_getToken;
      try {
        mainObject = new JSONObject(response);
        ok_getToken = mainObject.getBoolean("ok");
        if (ok_getToken){
          result = mainObject.getJSONObject("result");
          if (result.getBoolean("notif_new")){
            listener.onNewMassage();
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }, Throwable::printStackTrace)
    // Send Headers
    {
      @Override
      public Map<String, String> getHeaders()  {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", UserInfo.getApikey(context));
        return headers;
      }
    };
    request.setRetryPolicy( new DefaultRetryPolicy(
        5 * 1000,
        0,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    QuranApplication.getInstance().addToRequestQueue(request);
  }
  private interface smileListener{
    void onNewMassage();
  }
}
