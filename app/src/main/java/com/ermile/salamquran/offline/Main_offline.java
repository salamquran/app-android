package com.ermile.salamquran.offline;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.ermile.salamquran.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Character.toChars;

public class Main_offline extends AppCompatActivity {
    public static final String TAG = "Main_offline";

    Timer timer;
    MediaPlayer mediaPlayer;
    ArrayList<String> playlist = new ArrayList<>();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_offline);
        playlist.add("https://dl.salamquran.com/ayat/afasy-murattal-192/001001.mp3");
        playlist.add("https://dl.salamquran.com/ayat/afasy-murattal-192/001002.mp3");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(playlist.get(0)));
            mediaPlayer.prepare();
            mediaPlayer.start();
            timer = new Timer();
            if (playlist.size()>1) playNext();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void playNext() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(playlist.get(++i)));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    if (playlist.size() > i+1) {
                        playNext();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, mediaPlayer.getDuration()+100);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        timer.cancel();
        super.onDestroy();
    }
}
