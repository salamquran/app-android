package com.ermile.salamquran.android.salamquran.Learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.VideoShowActivity;
import com.ermile.salamquran.android.salamquran.api.LearnApi;

import org.json.JSONException;
import org.json.JSONObject;

public class LearnActivity_level_info extends AppCompatActivity {
  View box_image,box_title,box_desc;
  ImageView image;
  TextView title,desc;

  /*TryAgain*/
  private ProgressBar progressBar;
  private View viewTry;
  View boxLearn;

  @Override
  protected void onResume() {
    super.onResume();
    ((QuranApplication) getApplication()).refreshLocale(this, true);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_level_info);

    boxLearn = findViewById(R.id.box_learn);
    box_image = findViewById(R.id.box_image);
    box_title = findViewById(R.id.cardview_title);
    box_desc = findViewById(R.id.cardview_desc);
    image = findViewById(R.id.image_video);
    title = findViewById(R.id.title);
    desc = findViewById(R.id.desc);

    /*TryAgain*/
    viewTry = findViewById(R.id.itemTryAgain);
    progressBar = findViewById(R.id.progress);
    Button btnTry = viewTry.findViewById(R.id.btn_try_again);

    getItem();
    btnTry.setOnClickListener(v -> getItem());
  }

  private void getItem(){
    progressBar.setVisibility(View.VISIBLE);
    viewTry.setVisibility(View.GONE);
    LearnApi.levelInfo(
        getApplication(),
        getIntent().getStringExtra("id"),
        new LearnApi.levelInfoListener() {
          @Override
          public void onReceived(String Result) {
            addItem(Result);
            boxLearn.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
          }

          @Override
          public void onFiled() {
            boxLearn.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            viewTry.setVisibility(View.VISIBLE);
          }
        });
  }

  private void addItem(String Response){
    try {
      JSONObject object = new JSONObject(Response);
      if (!object.isNull("file")){
        String file = object.getString("file");
        if (!object.isNull("filepic")){
          Glide
              .with(getApplication())
              .load(object.getString("filepic"))
              .into(image);
        }else {
          Glide
              .with(getApplication())
              .load(file)
              .into(image);
        }
        image.setOnClickListener(v -> {
          Intent intent = new Intent(getApplication(), VideoShowActivity.class);
          intent.putExtra("video",file);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
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
