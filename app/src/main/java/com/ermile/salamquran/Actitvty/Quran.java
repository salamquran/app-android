package com.ermile.salamquran.Actitvty;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.Adaptor.QuranAdaptor;
import com.ermile.salamquran.Function.Utility.carateURL;
import com.ermile.salamquran.Item.itemQuran.ayat;
import com.ermile.salamquran.Item.item_PlayAudio;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Service.AudioForeground;
import com.ermile.salamquran.Static.tag;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quran extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    private TextView number_pageQuran , number_juzQuran,title_surahQuran;


    RtlViewPager viewpager;

    View stop,play;
    List<ayat> ayatList;
    List<item_PlayAudio> playAudioList;

    MediaPlayer mediaPlayer;

    int place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        mediaPlayer = new MediaPlayer();

        stop = findViewById(R.id.audio_stop);
        play = findViewById(R.id.audio_play);

        


        // Chang ID XML
        number_pageQuran = findViewById(R.id.number_pageQuran);
        number_juzQuran = findViewById(R.id.number_juzQuran);
        title_surahQuran = findViewById(R.id.title_surahQuran);
        viewpager = findViewById(R.id.view_pagers); // view page in XML

        // set
        QuranAdaptor PagerAdapter = new QuranAdaptor(getApplicationContext()); // add Adapter (in line 55)
        viewpager.setAdapter(PagerAdapter); // set Adapter to View pager in XML



        viewpager.setCurrentItem(Integer.valueOf(getIntent().getStringExtra("open_page")));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int position) {

                place =position;

                if (mediaPlayer != null && !mediaPlayer.isPlaying()){
                    playAudioList  = new ArrayList<>();
                    SQLiteDatabase mydb = new MyDatabase(getApplicationContext()).getWritableDatabase();
                    Cursor pageData = mydb.rawQuery("select * from quran_word where char_type = 'end'and page ="+position, null);

                    while (pageData.moveToNext()){
                        int page = pageData.getInt(pageData.getColumnIndex("page"));
                        int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                        int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                        int index = pageData.getInt(pageData.getColumnIndex("index"));
                        String url = carateURL.audio(getApplication(),sura+"",aya+"");
                        playAudioList.add(new item_PlayAudio(page,sura,aya,index,url));
                    }
                    pageData.close();
                    mydb.close();
                }

            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });





        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlaying();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                stopService(new Intent(getApplication(), AudioForeground.class));
            }
        });


    }

    int i = 0;

    private void startPlaying() {
        mediaPlayer = new MediaPlayer();
        try {
            if (playAudioList.get(i).getUrl() != null){
                mediaPlayer.setDataSource(playAudioList.get(i).getUrl());
                mediaPlayer.prepare();
                i++;
            }
        } catch (IOException ignored) {
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (playAudioList.size() != i){
            startPlaying();
            if (playAudioList.get(0).getPage() != place){
                viewpager.setCurrentItem(playAudioList.get(0).getPage());
                place = playAudioList.get(0).getPage();
            }
        }
        else {
            playAudioList.size();
            if (playAudioList.get(i-1).getPage() != 604){
                viewpager.setCurrentItem(playAudioList.get(i-1).getPage()+1);
                i = 0;
                startPlaying();
            }
            else{
                i = 0;
                Toast.makeText(this, "صدق الله العلی العظیم", Toast.LENGTH_SHORT).show();
            } 
            
        }
        Log.d(tag.important, i+" - onCompletion: "+playAudioList.size());
    }

}
