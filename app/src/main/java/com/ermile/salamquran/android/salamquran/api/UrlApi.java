package com.ermile.salamquran.android.salamquran.api;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;
import com.ermile.salamquran.android.salamquran.Utility.Url;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;

import org.json.JSONObject;

public class UrlApi {
  private String site,kingdom,domain,root,update = null;

  public UrlApi(Context context) {
    StringRequest request =
        new StringRequest(Request.Method.GET, Url.getAndroidDetail(context), response -> {
          try {
            JSONObject mainObject = new JSONObject(response);
            if (mainObject.getBoolean("ok")){
              JSONObject result = mainObject.getJSONObject("result");
              if (!result.isNull("url")){
                JSONObject url = result.getJSONObject("url");
                if (!url.isNull("site")){
                  site = url.getString("site");
                }
                if (!url.isNull("kingdom")){
                  kingdom = url.getString("kingdom");
                }
                if (!url.isNull("domain")){
                  domain = url.getString("domain");
                }
                if (!url.isNull("root")){
                  root = url.getString("root");
                }
                if (!url.isNull("update")){
                  update = url.getString("update");
                }
                SaveManager.get(context).save_url(site,kingdom,domain,root,update);
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }, Throwable::printStackTrace);
    request.setRetryPolicy(new DefaultRetryPolicy(
        5 * 1000,
        0,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    QuranApplication.getInstance().addToRequestQueue(request);
  }
}
