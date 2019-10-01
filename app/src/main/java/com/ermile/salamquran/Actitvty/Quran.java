package com.ermile.salamquran.Actitvty;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.Adaptor.QuranAdaptor;
import com.ermile.salamquran.Item.itemQuran.ayat;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.tag;

import java.util.ArrayList;
import java.util.List;

public class Quran extends AppCompatActivity {

    private TextView number_pageQuran , number_juzQuran,title_surahQuran;

    View stop,play;
    List<ayat> ayatList;
    List<sss> sssList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        stop = findViewById(R.id.audio_stop);
        play = findViewById(R.id.audio_play);

        


        // Chang ID XML
        number_pageQuran = findViewById(R.id.number_pageQuran);
        number_juzQuran = findViewById(R.id.number_juzQuran);
        title_surahQuran = findViewById(R.id.title_surahQuran);
        RtlViewPager viewpager = findViewById(R.id.view_pagers); // view page in XML

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

                sssList  = new ArrayList<>();

                SQLiteDatabase mydb = new MyDatabase(getApplicationContext()).getWritableDatabase();
                Cursor pageData = mydb.rawQuery("select aya,sura,page from quran_word where char_type = 'end'and page ="+position, null);

                while (pageData.moveToNext()){
                    int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                    int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                    int page = pageData.getInt(pageData.getColumnIndex("page"));
                    sssList.add(new sss(page+"",aya+""));
                }
                pageData.close();
                mydb.close();





            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < sssList.size(); i++) {
                    String page = sssList.get(i).getPage();
                    String atya = sssList.get(i).getAya();
                    Toast.makeText(Quran.this, page+"<-- page | aya -->"+atya, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public class sss{

        String page,aya;

        public sss(String page, String aya) {
            this.page = page;
            this.aya = aya;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getAya() {
            return aya;
        }

        public void setAya(String aya) {
            this.aya = aya;
        }
    }
}
