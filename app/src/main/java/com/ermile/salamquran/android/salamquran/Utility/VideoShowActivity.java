package com.ermile.salamquran.android.salamquran.Utility;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.android.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoShowActivity extends AppCompatActivity{
  PlayerView pvMain;
  SimpleExoPlayer absPlayerInternal;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    LANDSCAPE
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//    View
    setContentView(R.layout.activity_video_show);
    pvMain = findViewById(R.id.ep_video_view);

    String CONTENT_URL = getIntent().getStringExtra("video");
    startPlayingVideo(CONTENT_URL);

  }

  //
  private void startPlayingVideo( String CONTENT_URL) {
    Context ctx = getApplicationContext();

    TrackSelector trackSelectorDef = new DefaultTrackSelector();

    absPlayerInternal = ExoPlayerFactory.newSimpleInstance(ctx, trackSelectorDef);

    String userAgent = Util.getUserAgent(ctx, "Video");

    DefaultDataSourceFactory defdataSourceFactory = new DefaultDataSourceFactory(ctx,userAgent);
    Uri uriOfContentUrl = Uri.parse(CONTENT_URL);
    MediaSource mediaSource = new ProgressiveMediaSource.Factory(defdataSourceFactory).createMediaSource(uriOfContentUrl);

    absPlayerInternal.prepare(mediaSource);
    absPlayerInternal.setPlayWhenReady(true);

    pvMain.setPlayer(absPlayerInternal);

  }

  @Override
  protected void onPause() {
    super.onPause();
    stopPlayer();
  }

  private void stopPlayer(){
    pvMain.setPlayer(null);
    absPlayerInternal.release();
    absPlayerInternal = null;
  }
}
