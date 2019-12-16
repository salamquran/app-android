package com.ermile.salamquran.android.salamquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Mag.MagActivity;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ImageViewerActivity extends AppCompatActivity {

  RecyclerViewPager recyclerView;
  Adapter.slider adaptor;
  ArrayList<String> item;
  LinearLayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_viewer);

    recyclerView = findViewById(R.id.recyclerViewPager);
    item = new ArrayList<>();
    adaptor = new Adapter.slider(getApplicationContext(),item,true);
    recyclerView.setAdapter(adaptor);
    layoutManager =
        new LinearLayoutManager(
            getApplication(),
            LinearLayoutManager.HORIZONTAL,
            false);

    if (getIntent().getExtras() != null){
      Intent e = getIntent();
      String image = e.getStringExtra("image");
      item.add(image);

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
