package com.ermile.salamquran.Actitvty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.Adaptor.QuranAdaptor;
import com.ermile.salamquran.Function.Utility.Download;
import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Function.Utility.carateURL;
import com.ermile.salamquran.Item.item_PlayAudio;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quran extends AppCompatActivity implements MediaPlayer.OnCompletionListener, ViewPager.OnPageChangeListener {

    RtlViewPager viewpager;
    QuranAdaptor PagerAdapter;

    List<item_PlayAudio> playAudioList;
    MediaPlayer mediaPlayer;
    Integer ayaNumber = 0;

    RelativeLayout boxMediaControl;
    ImageView btn_next,btn_back,btn_play,btn_pause,btn_stop;
    ImageView imageQari;
    TextView qariName,typeQari;
    int place;
    boolean besmellahIsPlaying = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quran);

        // Chang ID XML
        viewpager = findViewById(R.id.view_pagers);  // RTL viewpager in XML
        boxMediaControl = findViewById(R.id.box_media_contoroler);
        btn_back = findViewById(R.id.back);          // MediaControl (Back Audio)
        btn_next = findViewById(R.id.next);          // MediaControl (Next Audio)
        btn_play = findViewById(R.id.play);         // MediaControl (Play Audio)
        btn_pause = findViewById(R.id.pause);       // MediaControl (Pause Audio)
        btn_stop = findViewById(R.id.stop);         // MediaControl (Stop Audio)

        imageQari = findViewById(R.id.imageQari);
        qariName = findViewById(R.id.nameQari);
        typeQari = findViewById(R.id.typeQari);
        setNameQari();

        /*Change Qari*/
        imageQari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runAlert();
            }
        });


        // set
        PagerAdapter = new QuranAdaptor(new QuranAdaptor.onTochListener() {
            @Override
            public void wordOnclickListener() {
               changeTransitionBoxMediaControl();
            }

            @Override
            public void wordOnLongclickListener(int indexAya) {

            }
        });
        // add Adapter
        viewpager.setAdapter(PagerAdapter); // set Adapter to View pager in XML
        viewpager.setOffscreenPageLimit(1);
        String getPage_FromListQuran = Objects.requireNonNull(getIntent().getStringExtra("open_page"));
        viewpager.setCurrentItem(Integer.valueOf(getPage_FromListQuran));
        viewpager.clearOnPageChangeListeners();
        viewpager.addOnPageChangeListener(this);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_stop.setVisibility(View.VISIBLE);
                btn_play.setVisibility(View.GONE);
                btn_next.setVisibility(View.VISIBLE);
                btn_back.setVisibility(View.VISIBLE);
                stopSound();
                playSound();


            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSound();
                clickOnStop();
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseSound();
                btn_play.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.GONE);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSound();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSound();
            }
        });
    }

    /*Change Transition Box Media Control*/
    private void changeTransitionBoxMediaControl(){
        int transToBottom = (int) this.getResources().getDimension(R.dimen._60sdp);
        if (boxMediaControl.getTranslationY() != 0f){
            boxMediaControl.animate().setDuration(300).translationY(0f);
        }else {
            boxMediaControl.animate().setDuration(300).translationY(transToBottom);
        }
    }

    /*Next Aya*/
    private void nextSound() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        if (ayaIsEND()) {
            if (playAudioList.get(0).getPage() != 604) {
                viewpager.setCurrentItem(playAudioList.get(0).getPage() + 1, true);
                playSound();
            } else {
                Toast.makeText(this, "صدق الله العلی العظیم", Toast.LENGTH_SHORT).show();
            }
        } else {
            ayaNumber++;
            playSound();
        }
    }

    /*Back Aya*/
    private void backSound() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        if (ayaNumber == 0) {
            if (playAudioList.get(0).getPage() != 604) {
                viewpager.setCurrentItem(playAudioList.get(0).getPage() - 1, true);
                ayaNumber = playAudioList.size() -1;
                playSound();
            } else {
                Toast.makeText(this, "صدق الله العلی العظیم", Toast.LENGTH_SHORT).show();
            }
        } else {
            ayaNumber--;
            playSound();
        }
    }

    /*Play AudioPlayer*/
    private void playSound() {
        boolean playFromStorage = false;
        int aya = playAudioList.get(ayaNumber).getAya();
        int sura = playAudioList.get(ayaNumber).getVers();
        int page = playAudioList.get(ayaNumber).getPage();
        int index = playAudioList.get(ayaNumber).getIndex();
        String pathAudio = playAudioList.get(ayaNumber).getUrl();
        if (!besmellahIsPlaying
                && aya == 1
                && sura != 1
                && sura != 9) {
            viewpager.setCurrentItem(page, true);
            Objects.requireNonNull(viewpager.getAdapter()).notifyDataSetChanged();
            setBgPlaying(index,true);
            playBesmellah();
        } else {
            besmellahIsPlaying = false;
            String qariName = SaveManager.get(this).getstring_appINFO().get(SaveManager.qari_Slug);
            if (FileManager.findFile_storage("/"+qariName+"/"+sura+"/",aya+ format.mp3)){
                playFromStorage = true;
                File fileBesmellah = FileManager.getFile_storage("/"+qariName+"/"+sura+"/",aya+ format.mp3);
                pathAudio = fileBesmellah.getPath();
                if (!ayaIsEND()){
                    String urlAyaNext = playAudioList.get(ayaNumber+1).getUrl();
                    int ayaNext = playAudioList.get(ayaNumber+1).getAya();
                    int suraNext = playAudioList.get(ayaNumber+1).getVers();
                    if (!FileManager.findFile_storage("/"+qariName+"/"+suraNext+"/",ayaNext+ format.mp3)){
                        Download.Aya(this,urlAyaNext,qariName,String.valueOf(suraNext),String.valueOf(ayaNext));
                    }

                }

            }
            else {
                Download.Aya(this,pathAudio,qariName,String.valueOf(sura),String.valueOf(aya));
                if (!ayaIsEND() && ayaNumber != 0){
                    String urlAyaNext = playAudioList.get(ayaNumber+1).getUrl();
                    int ayaNext = playAudioList.get(ayaNumber+1).getAya();
                    int suraNext = playAudioList.get(ayaNumber+1).getVers();
                    Download.Aya(this,urlAyaNext,qariName,String.valueOf(suraNext),String.valueOf(ayaNext));
                }
            }
            if (!playFromStorage && !isNetworkAvailable()){
                Toast.makeText(this, "به اینترنت متصل شوید", Toast.LENGTH_SHORT).show();
                stopSound();
                clickOnStop();
            }
            else {
                try {
                    if (ayaNumber < playAudioList.size()) {
                        viewpager.setCurrentItem(playAudioList.get(ayaNumber).getPage(), true);
                        Objects.requireNonNull(viewpager.getAdapter()).notifyDataSetChanged();
                        setBgPlaying(playAudioList.get(ayaNumber).getIndex(),true);
                        mediaPlayer = new MediaPlayer();
                        Log.d(tag.important, "playSound: "+pathAudio);
                        mediaPlayer.setDataSource(pathAudio);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(this);
                    }

                } catch (IOException ignored) {
                }
            }

        }
    }

    /*Stop AudioPlayer*/
    private void stopSound() {
        if (mediaPlayer != null) {
            ayaNumber = 0;
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            createListAudioAya(viewpager.getCurrentItem());
        }
    }

    /*Pause AudioPlayer*/
    private void pauseSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /*OnCompletion Audio Quran For Play Auto Next Aya*/
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (ayaIsEND()) {
            if (playAudioList.get(0).getPage() != 604) {
                stopSound();
                viewpager.setCurrentItem(playAudioList.get(0).getPage() + 1, true);
                playSound();
            } else {
                stopSound();
                Toast.makeText(this, "صدق الله العلی العظیم", Toast.LENGTH_SHORT).show();
            }
        } else {
            ayaNumber++;
            playSound();
        }
    }

    /*Hi Light Text in ViewPager*/
    private void setBgPlaying(int TagOfAya , boolean isAudioQuran) {
        RelativeLayout relativeLayout = (RelativeLayout) viewpager.getChildAt(0);
        LinearLayout background_slide = (LinearLayout) relativeLayout.getChildAt(2);
        for (int rq = 0; rq <= background_slide.getChildCount(); rq++) {
            LinearLayout rowQuran = (LinearLayout) background_slide.getChildAt(rq);
            if (rowQuran != null) {
                for (int wq = 0; wq <= rowQuran.getChildCount(); wq++) {
                    TextView wordQuran = (TextView) rowQuran.getChildAt(wq);
                    if (wordQuran != null) {
                        if (wordQuran.getTag().toString().equals(TagOfAya+"")) {

                            if (isAudioQuran){
                                wordQuran.setTextColor(Color.BLUE);
                            }
                            else {
                                wordQuran.setBackgroundColor(Color.parseColor("#ADC9A1"));
                            }
                        }
                        else {
                            if (isAudioQuran){
                                wordQuran.setTextColor(Color.BLACK);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        place = position;
        createListAudioAya(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    private void playBesmellah() {
        String qariName = SaveManager.get(this).getstring_appINFO().get(SaveManager.qari_Slug);
        String pathAudio = carateURL.besmellah(this);
        if (FileManager.findFile_storage("/"+qariName+"/1/","1"+ format.mp3)){
            File fileBesmellah = FileManager.getFile_storage("/"+qariName+"/1/","1"+ format.mp3);
            pathAudio = fileBesmellah.getPath();

        }
        else {
            Download.Aya(this,pathAudio,qariName,"1","1");
        }
        try {
            mediaPlayer = new MediaPlayer();
            Log.d(tag.important, "playBesmellah: "+pathAudio);
            mediaPlayer.setDataSource(pathAudio);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    besmellahIsPlaying = true;
                    playSound();
                }
            });

        } catch (IOException ignored) {
        }
    }


    private void createListAudioAya(int page){
        if (mediaPlayer == null) {
            ayaNumber = 0;
            playAudioList = new ArrayList<>();
            SQLiteDatabase mydb = new MyDatabase(getApplicationContext()).getReadableDatabase();
            Cursor pageData = mydb.rawQuery("select * from quran_word where char_type = 'end' and page =" + page, null);
            while (pageData.moveToNext()) {
                int pages = pageData.getInt(pageData.getColumnIndex("page"));
                int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                int index = pageData.getInt(pageData.getColumnIndex("index"));
                String url = carateURL.audio(getApplication(), sura + "", aya + "");
                playAudioList.add(new item_PlayAudio(pages, sura, aya, index, url));
            }
            pageData.close();
            mydb.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSound();
    }

    private boolean ayaIsEND() {
        int AudioAya = playAudioList.size() - 1;
        return AudioAya == ayaNumber;
    }



    /*Oder Method*/

    private void runAlert() {
        QariList qariList = new QariList(Quran.this);
        qariList.setTitle("قاری خود را انتخاب کنید");
        qariList.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                stopSound();
                clickOnStop();
                setNameQari();
            }
        });
        qariList.show();
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void setNameQari(){
        String qari_Name = SaveManager.get(this).getstring_appINFO().get(SaveManager.qari_Name);
        String qari_Slug = SaveManager.get(this).getstring_appINFO().get(SaveManager.qari_Slug);
        String qari_Type = SaveManager.get(this).getstring_appINFO().get(SaveManager.qari_Type);
        qariName.setText(qari_Name);
        typeQari.setText(qari_Type);
        if (FileManager.findFile_storage(file.qari_image,qari_Slug+format.png)){
            File imgFiles = FileManager.getFile_storage(file.qari_image,qari_Slug+format.png);
            if(imgFiles.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFiles.getAbsolutePath());
                imageQari.setImageBitmap(myBitmap);
            }
        }
        createListAudioAya(viewpager.getCurrentItem());


    }

    private void clickOnStop(){
        btn_stop.setVisibility(View.GONE);
        btn_play.setVisibility(View.VISIBLE);
        btn_next.setVisibility(View.GONE);
        btn_back.setVisibility(View.GONE);
    }
}