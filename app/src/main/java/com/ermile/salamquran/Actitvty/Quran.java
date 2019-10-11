package com.ermile.salamquran.Actitvty;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.viewpager.widget.ViewPager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.Adaptor.QuranAdaptor;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Function.Utility.carateURL;
import com.ermile.salamquran.Item.item_PlayAudio;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.value;

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

    AppCompatImageButton btn_next, btn_back,
            btn_play, btn_pause, btn_stop;
    ImageButton btn_changeQari;
    int place;
    boolean besmellahIsPlaying = false;

    /*Test*/
    String result = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quran);






        // Chang ID XML
        viewpager = findViewById(R.id.view_pagers);    // RTL viewpager in XML
        btn_back = findViewById(R.id.btn_bakc);        // MediaControl (Back Audio)
        btn_next = findViewById(R.id.btn_next);        // MediaControl (Next Audio)
        btn_play = findViewById(R.id.btn_play);        // MediaControl (Play Audio)
        btn_pause = findViewById(R.id.btn_pause);      // MediaControl (Pause Audio)
        btn_stop = findViewById(R.id.btn_stop);        // MediaControl (Stop Audio)
        btn_changeQari = findViewById(R.id.change_qari);


        /*Change Qari*/
        btn_changeQari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSound();
                runAlert();
            }
        });


        // set
        PagerAdapter = new QuranAdaptor(); // add Adapter
        viewpager.setAdapter(PagerAdapter); // set Adapter to View pager in XML
        viewpager.setOffscreenPageLimit(0);
        String getPage_FromListQuran = Objects.requireNonNull(getIntent().getStringExtra("open_page"));
        viewpager.setCurrentItem(Integer.valueOf(getPage_FromListQuran));
        viewpager.clearOnPageChangeListeners();
        viewpager.addOnPageChangeListener(this);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSound();
                playSound();
                btn_pause.setVisibility(View.VISIBLE);
                btn_play.setVisibility(View.GONE);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSound();
                btn_play.setVisibility(View.VISIBLE);
                btn_pause.setVisibility(View.GONE);
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

        if (!besmellahIsPlaying
                && playAudioList.get(ayaNumber).getAya() == 1
                && playAudioList.get(ayaNumber).getVers() != 1
                && playAudioList.get(ayaNumber).getVers() != 9) {
            viewpager.setCurrentItem(playAudioList.get(ayaNumber).getPage(), true);
            Objects.requireNonNull(viewpager.getAdapter()).notifyDataSetChanged();
            setBgPlaying();
            playBesmellah();
        } else {
            besmellahIsPlaying = false;
            try {
                if (playAudioList.get(ayaNumber).getUrl() != null) {
                    if (ayaNumber < playAudioList.size()) {
                        viewpager.setCurrentItem(playAudioList.get(ayaNumber).getPage(), true);
                        Objects.requireNonNull(viewpager.getAdapter()).notifyDataSetChanged();
                        setBgPlaying();
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(playAudioList.get(ayaNumber).getUrl());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(this);
                    }
                }

            } catch (IOException ignored) {
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
            viewpager.setCurrentItem(viewpager.getCurrentItem());
            Objects.requireNonNull(viewpager.getAdapter()).notifyDataSetChanged();
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
    private void setBgPlaying() {
        RelativeLayout relativeLayout = (RelativeLayout) viewpager.getChildAt(0);
        LinearLayout background_slide = (LinearLayout) relativeLayout.getChildAt(2);
        for (int rq = 0; rq <= background_slide.getChildCount(); rq++) {
            LinearLayout rowQuran = (LinearLayout) background_slide.getChildAt(rq);
            if (rowQuran != null) {
                for (int wq = 0; wq <= rowQuran.getChildCount(); wq++) {
                    TextView wordQuran = (TextView) rowQuran.getChildAt(wq);
                    if (wordQuran != null) {
                        if (wordQuran.getTag().toString().equals(playAudioList.get(ayaNumber).getIndex() + "")) {
                            wordQuran.setTextColor(Color.BLUE);
                        } else {
                            wordQuran.setTextColor(Color.BLACK);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSound();
    }

    private boolean ayaIsEND() {
        int AudioAya = playAudioList.size() - 1;
        return AudioAya == ayaNumber;
    }

    private void playBesmellah() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(carateURL.besmellah(this));
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





    /*Oder Method*/

    private void runAlert() {
        final String[] items = value.qari;
        new AlertDialog.Builder(this)
                .setTitle("قاری خود را انتخاب کنید")
                .setCancelable(false)
                .setSingleChoiceItems(items, 0, null)
                .setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SaveManager.get(getApplication()).change_qari(result);
                        Objects.requireNonNull(viewpager.getAdapter()).notifyDataSetChanged();
                        Toast.makeText(Quran.this, result + "شروع به تلاوت", Toast.LENGTH_SHORT).show();
                        createListAudioAya(place);
                    }
                })
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        result = items[i];
                    }
                })
                .create()
                .show();
    }
}