package com.ermile.salamquran.android.salamquran.checkVersion;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;
import com.ermile.salamquran.android.salamquran.Utility.Url;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;

import org.json.JSONObject;

public class DeprecatedVersionApi {
  public DeprecatedVersionApi(Context context) {
    StringRequest request =
        new StringRequest(Request.Method.GET, Url.getAndroidDetail(context), response -> {
          try {
            JSONObject mainObject = new JSONObject(response);
            if (mainObject.getBoolean("ok")){
              JSONObject result = mainObject.getJSONObject("result");

              if (!result.isNull("version")){
                JSONObject version = result.getJSONObject("version");
                if (!version.isNull("deprecated")){
                  int deprecated = version.getInt("deprecated");
                  if (UserInfo.versionCode <= deprecated && deprecated != 0){
                    Log.d("amingoli", "DeprecatedVersionApi: true "+deprecated);
                    SaveManager.get(context).save_status_deprecated_version(true);
                  }else {
                    SaveManager.get(context).save_status_deprecated_version(false);
                    Log.d("amingoli", "DeprecatedVersionApi: false "+deprecated);
                  }
                }
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
