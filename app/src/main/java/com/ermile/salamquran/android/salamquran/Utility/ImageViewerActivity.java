package com.ermile.salamquran.android.salamquran.Utility;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageViewerActivity extends AppCompatActivity {


  PhotoView photoView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_viewer);
    photoView = findViewById(R.id.photo_view);

    if (getIntent().getExtras() != null){
      Glide
          .with(getApplication())
          .load(getIntent().getStringExtra("image"))
          .into(photoView);
    }
  }


}
