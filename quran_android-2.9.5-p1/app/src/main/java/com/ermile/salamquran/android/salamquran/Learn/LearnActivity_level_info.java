package com.ermile.salamquran.android.salamquran.Learn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.api.LearnApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LearnActivity_level_info extends AppCompatActivity {
  View box_image,box_title,box_desc;
  ImageView image;
  TextView title,desc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_level_info);

    box_image = findViewById(R.id.box_image);
    box_title = findViewById(R.id.cardview_title);
    box_desc = findViewById(R.id.cardview_desc);
    image = findViewById(R.id.image);
    title = findViewById(R.id.title);
    desc = findViewById(R.id.desc);

    LearnApi.levelInfo(
        getApplication(),
        getIntent().getStringExtra("id"),
        new LearnApi.levelInfoListener() {
      @Override
      public void onReceived(String Result) {
        addItem(Result);
      }

      @Override
      public void onFiled() {

      }
    });

  }

  private void addItem(String Response){
    try {
      JSONObject object = new JSONObject(Response);
      if (!object.isNull("file")){
        if (!object.isNull("filepic")){
          Glide
              .with(getApplication())
              .load(object.getString("filepic"))
              .into(image);
        }else {
          Glide
              .with(getApplication())
              .load(object.getString("file"))
              .into(image);
        }
      }

      if (!object.isNull("title")){
        box_title.setVisibility(View.VISIBLE);
        title.setText(object.getString("title"));
      }

      if (!object.isNull("desc")){
        box_desc.setVisibility(View.VISIBLE);
        desc.setText(object.getString("desc"));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
