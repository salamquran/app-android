package com.ermile.salamquran.Actitvty.View;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.R;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = (VideoView)findViewById(R.id.your_video_view);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        String str = getIntent().getStringExtra("url");
        Uri uri = Uri.parse(str);

        videoView.setVideoURI(uri);

        videoView.requestFocus();
        videoView.start();
    }
}
