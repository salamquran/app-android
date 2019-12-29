package com.ermile.salamquran.android.salamquran.Intro;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.salamquran.Utility.Json;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;
import com.ermile.salamquran.android.salamquran.Utility.Url;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

public class IntroApi {
  private Context context;

  public IntroApi(Context context) {
    this.context = context;
    setIntroJsonOffline();
    StringRequest request =
        new StringRequest(Request.Method.GET, Url.getAndroidDetail(context), response -> {
          try {
            JSONObject mainObject = new JSONObject(response);
            if (mainObject.getBoolean("ok")){
              JSONObject result = mainObject.getJSONObject("result");

              JSONArray intro = result.getJSONArray("intro");
              SaveManager.get(context).save_json_intro(String.valueOf(intro));

            }
          } catch (Exception e) {
            e.printStackTrace();
            setIntroJsonOffline();
          }
        }, error -> setIntroJsonOffline());
    request.setRetryPolicy(new DefaultRetryPolicy(
        5 * 1000,
        0,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    QuranApplication.getInstance().addToRequestQueue(request);
  }

  private void setIntroJsonOffline(){
    String lang = UserInfo.getAppLanguage(context);
    switch (lang){
      case "en":
        SaveManager.get(context).save_json_intro(Json.defaultValue.intro_en);
        break;
      case "fa":
        SaveManager.get(context).save_json_intro(Json.defaultValue.intro_fa);
        break;
      case "ar":
        SaveManager.get(context).save_json_intro(Json.defaultValue.intro_ar);
        break;
    }
  }
}
