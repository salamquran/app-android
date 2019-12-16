package com.ermile.salamquran.android.salamquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Mag.MagActivity;
import com.github.chrisbanes.photoview.PhotoView;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ImageViewerActivity extends AppCompatActivity {

/*  RecyclerViewPager recyclerView;
  Adapter.slider adaptor;
  ArrayList<String> item;
  LinearLayoutManager layoutManager;*/

  PhotoView photoView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_viewer);
    photoView = findViewById(R.id.photo_view);

    /*recyclerView = findViewById(R.id.recyclerViewPager);
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
      if (e.getStringExtra("image") != null){
        String image = e.getStringExtra("image");
        item.add(image);
      }
      if (e.getStringExtra("gallery") != null){
        String[] result = e.getStringArrayExtra("gallery");
        addItem(result);
      }
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
      adaptor.notifyDataSetChanged();}*/
    if (getIntent().getExtras() != null){
      Glide
          .with(getApplication())
          .load(getIntent().getStringExtra("image"))
          .into(photoView);
    }
  }


}
