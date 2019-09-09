package com.ermile.salamquran.Actitvty;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.Adaptor.QuranAdaptor;
import com.ermile.salamquran.R;

public class Quran extends AppCompatActivity {

    int tagOnclickAudio;

    MediaPlayer mPlayer = new MediaPlayer();

    QuranAdaptor PagerAdapter;  // for View page
    RtlViewPager viewpager; //  for dots & Button in XML
    private TextView number_pageQuran , number_juzQuran,title_surahQuran;
    public int count = 605; // Slide number


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);


        // Chang ID XML
        number_pageQuran = findViewById(R.id.number_pageQuran);
        number_juzQuran = findViewById(R.id.number_juzQuran);
        title_surahQuran = findViewById(R.id.title_surahQuran);
        viewpager = findViewById(R.id.view_pagers); // view page in XML

        // set
        PagerAdapter = new QuranAdaptor(getApplicationContext()); // add Adapter (in line 55)
        viewpager.setAdapter(PagerAdapter); // set Adapter to View pager in XML



        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(final int position) {
                /*number_pageQuran.setText(String.valueOf(position));
                page = "https://salamquran.com/fa/api/v6/page/wbw?index="+String.valueOf(position);
                try {
                    String textTop_surah , textTop_juz ;
                    String jsonText = readFromMyFile(Value.jsonFile_QuranWBW);
                    JSONArray jsonObject = new JSONArray(jsonText);
                    for (int i = 0 ; i<= jsonObject.length(); i++) {
                        JSONObject get_WordQuran = jsonObject.getJSONObject(i);
                        String page = get_WordQuran.getString("page");
                        if (position == Integer.valueOf(page)){

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        viewpager.setCurrentItem(Integer.valueOf(getIntent().getStringExtra("open_page")));

    }
}
