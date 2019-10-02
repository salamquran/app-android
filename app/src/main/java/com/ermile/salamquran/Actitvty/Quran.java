package com.ermile.salamquran.Actitvty;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
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
import com.ermile.salamquran.Static.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quran extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    TextView title_juz ,
             title_vars;


    RtlViewPager viewpager;

    View stop,play;
    List<ayat> ayatList;
    List<item_PlayAudio> playAudioList;

    MediaPlayer mediaPlayer;

    int place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quran);


        // Chang ID XML
        title_juz = findViewById(R.id.top_title_juz);
        title_vars = findViewById(R.id.top_title_vers);
        viewpager = findViewById(R.id.view_pagers); // view page in XML

        stop = findViewById(R.id.audio_stop);
        play = findViewById(R.id.audio_play);










        final LinearLayout mediaContoroler = findViewById(R.id.media_contoroler);

        title_juz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaContoroler.getTranslationY() < 20){
                    Toast.makeText(Quran.this, ""+mediaContoroler.getTranslationY(), Toast.LENGTH_SHORT).show();
                    mediaContoroler.animate().translationY(160).setDuration(300);
                }
                else {
                    mediaContoroler.animate().translationY(0).setDuration(300);

                }
            }
        });














        




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

                if (mediaPlayer == null ){
                    playAudioList  = new ArrayList<>();
                    SQLiteDatabase mydb = new MyDatabase(getApplicationContext()).getWritableDatabase();
                    Cursor pageData = mydb.rawQuery("select * from quran_word where char_type = 'end' and page ="+position, null);

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
                stopPlaying();
                startPlaying();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
            }
        });


    }

    int i = 0;

    private void startPlaying() {
        try {
            if (playAudioList.get(i).getUrl() != null){
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(playAudioList.get(i).getUrl());
                mediaPlayer.prepare();
                mediaPlayer.start();
                i++;
            }

        } catch (IOException ignored) {
        }
        mediaPlayer.setOnCompletionListener(this);
    }


    private void stopPlaying() {
        if (mediaPlayer !=null){
            i = 0;
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }else {
            i=0;
        }
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
            if (playAudioList.get(0).getPage() != 604){
                stopPlaying();
                viewpager.setCurrentItem(playAudioList.get(0).getPage()+1);
                startPlaying();
            }
            else{
                stopPlaying();
                Toast.makeText(this, "صدق الله العلی العظیم", Toast.LENGTH_SHORT).show();
            } 
            
        }
        Log.d(tag.important, i+" - onCompletion: "+playAudioList.size());
    }




    private void getAya(RtlViewPager viewpager){

    }

}
