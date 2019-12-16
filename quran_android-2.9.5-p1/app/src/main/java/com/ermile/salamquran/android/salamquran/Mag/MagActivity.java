package com.ermile.salamquran.android.salamquran.Mag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Adapter;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MagActivity extends AppCompatActivity {

  RecyclerViewPager recyclerView;
  Adapter.slider adaptor;
  ArrayList<String> item;
  LinearLayoutManager layoutManager;

  TextView title,subTitle,desc;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mag);
    recyclerView = findViewById(R.id.recyclerViewPager);
    item = new ArrayList<>();
    adaptor = new Adapter.slider(getApplicationContext(),item);
    recyclerView.setAdapter(adaptor);
    layoutManager =
        new LinearLayoutManager(
            getApplication(),
            LinearLayoutManager.HORIZONTAL,
            false);

    title = findViewById(R.id.title);
    subTitle = findViewById(R.id.short_desc);
    desc = findViewById(R.id.desc);

    if (getIntent().getExtras() != null){
      Intent e = getIntent();
      String image = e.getStringExtra("image");
      item.add(image);

      String title_i = e.getStringExtra("title");
      String excerpt = e.getStringExtra("excerpt");
      String content = e.getStringExtra("content");

      title.setText(title_i);
      subTitle.setText(excerpt);
      desc.setText(Html.fromHtml(content));

      if (e.getStringExtra("gallery") != null){
        String result = e.getStringExtra("gallery");
        addItem(result);
      }
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
      adaptor.notifyDataSetChanged();


    }



  }

  private void addItem(String Response){
    try {
      JSONArray array = new JSONArray(Response);
      for (int i = 0; i < array.length(); i++) {
        item.add(array.getString(i));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
