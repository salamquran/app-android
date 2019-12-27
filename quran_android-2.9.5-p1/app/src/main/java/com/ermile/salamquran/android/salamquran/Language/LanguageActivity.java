package com.ermile.salamquran.android.salamquran.Language;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.ColorUtil;
import com.ermile.salamquran.android.salamquran.Utility.Json;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;
import com.ermile.salamquran.android.salamquran.Utility.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  LanguageAdapter adapter;
  List<LanguageModel> item;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_language);

    recyclerView = findViewById(R.id.recycler_view);
    item = new ArrayList<>();
    adapter = new LanguageAdapter(item, this, this::restartActivity);
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    LinearLayoutManager sLayoutManager =
        new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
    GetLanguage();
    recyclerView.setLayoutManager(sLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    adapter.notifyDataSetChanged();
  }

  /*Get Language*/
  void GetLanguage() {
    try {
      String respone = Json.defaultValue.appLanguage;
      JSONObject jsonOffline = new JSONObject(respone);
      JSONObject result = jsonOffline.getJSONObject("result");
      Iterator<?> keys = result.keys();
      while (keys.hasNext()) {
        String key = (String) keys.next();
        JSONObject lang_key = result.getJSONObject(key);
        if (result.get(key) instanceof JSONObject) {
          if (UserInfo.getAppLanguage(getApplication())
              .equals(lang_key.getString("name"))) {
            item.add(new LanguageModel(
                lang_key.getString("localname"),
                lang_key.getString("name"),
                true,
                lang_key.getString("api_url")));
          } else {
            item.add(new LanguageModel(
                lang_key.getString("localname"),
                lang_key.getString("name"),
                false,
                lang_key.getString("api_url")));
          }
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void restartActivity() {
    ((QuranApplication) getApplication()).refreshLocale(this, true);
    finish();
  }
}
