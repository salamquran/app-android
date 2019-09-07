package com.ermile.salamquran.Actitvty;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.Adaptor.LanguageAdaptor;
import com.ermile.salamquran.Function.FileManager.ReadFile;
import com.ermile.salamquran.Function.SaveManager;
import com.ermile.salamquran.Item.item_Language;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Language extends AppCompatActivity {


    RecyclerView relv_Language;
    List<item_Language> mItem = new ArrayList<>();
    LanguageAdaptor mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        /*add RecyclerView and Adapter*/
        relv_Language = findViewById(R.id.lv_Language);
        mAdapter = new LanguageAdaptor(mItem, this);

        /*Set*/
        LinearLayoutManager sLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        relv_Language.setLayoutManager(sLayoutManager);
        relv_Language.setItemAnimator(new DefaultItemAnimator());
        relv_Language.setHasFixedSize(true);
        relv_Language.setAdapter(mAdapter);
        GetLanguage();
    }

    /*Get Language*/
    void GetLanguage() {
        String appLanguage = SaveManager.get(getApplicationContext()).getstring_appINFO().get(SaveManager.appLanguage);
        try {
            String Json_text = new ReadFile().ReadFile(this,file.setting, format.json);
            JSONObject jsonOffline = new JSONObject(Json_text);
            boolean ok = jsonOffline.getBoolean("ok");
            JSONObject result = jsonOffline.getJSONObject("result");
            JSONObject lang_list = result.getJSONObject("lang_list");
            Iterator<?> keys = lang_list.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                JSONObject lang_key = lang_list.getJSONObject(key);
                if (lang_list.get(key) instanceof JSONObject) {
                    if (appLanguage == lang_key.getString("name")) {
                        mItem.add(new item_Language(
                                lang_key.getString("localname"),
                                lang_key.getString("name"),
                                View.VISIBLE));
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mItem.add(new item_Language(
                                lang_key.getString("localname"),
                                lang_key.getString("name"),
                                View.INVISIBLE));
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
