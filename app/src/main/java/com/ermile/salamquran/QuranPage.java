package com.ermile.salamquran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.saveData.Value;

import java.io.IOException;

public class QuranPage extends AppCompatActivity {
    private static String TAG = "QuranPage";
    int tagOnclickAudio;

    MediaPlayer mPlayer = new MediaPlayer();

    ViewpagersAdapter PagerAdapter;  // for View page
    RtlViewPager viewpager; //  for dots & Button in XML
    private TextView number_pageQuran , number_juzQuran,title_surahQuran;
    public int count = 605; // Slide number

    getUrlAudio urlAudio = new getUrlAudio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_page);



        // Chang ID XML
        number_pageQuran = findViewById(R.id.number_pageQuran);
        number_juzQuran = findViewById(R.id.number_juzQuran);
        title_surahQuran = findViewById(R.id.title_surahQuran);
        viewpager = findViewById(R.id.view_pagers); // view page in XML

        // set
        PagerAdapter = new ViewpagersAdapter(this); // add Adapter (in line 55)
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


    private int getItem(int i) {
        return viewpager.getCurrentItem() + i;
    }



    /**
     * This Moder Adapter
     * View Pager Adapter
     */
    class ViewpagersAdapter extends androidx.viewpager.widget.PagerAdapter {

        private Context context;
        private LayoutInflater inflater;

        LinearLayout linequran_1,linequran_2,linequran_3,linequran_4,linequran_5,
                     linequran_6,linequran_7,linequran_8,linequran_9,linequran_10,
                     linequran_11,linequran_12,linequran_13,linequran_14,linequran_15;

        ViewpagersAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount()
        {
            return count;
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
            // Static Methods
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.item_modle_quranpage , container , false);
            final LinearLayout background_slide = view.findViewById(R.id.background_slide);

            LinearLayout linearLayout_Line = null;
            TextView TextQuran_textview = null ;

            int testLine =0;

            Typeface font_nabi=ResourcesCompat.getFont(context, R.font.font_nabi);
            Typeface font_p1 = ResourcesCompat.getFont(context, R.font.p1);
            Typeface font_p5 = ResourcesCompat.getFont(context, R.font.p5);
            final Typeface font_bismellah =ResourcesCompat.getFont(context, R.font.bismillah);



            SQLiteDatabase mydb = new MyDatabase(QuranPage.this).getWritableDatabase();
            Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);
            int is = 0;
            while (pageData.moveToNext()) {
                String text = pageData.getString(pageData.getColumnIndex("text"));
                String code = pageData.getString(pageData.getColumnIndex("code"));
                final int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                final int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                String charType = pageData.getString(pageData.getColumnIndex("char_type"));
                int line = pageData.getInt(pageData.getColumnIndex("line"));
                int page = pageData.getInt(pageData.getColumnIndex("page"));
                int positions = pageData.getInt(pageData.getColumnIndex("position"));
                String audio = pageData.getString(pageData.getColumnIndex("audio"));


                if (charType.equals("end")){
                    Log.d(TAG, ""+aya+"\n"+page);
                }


                if(testLine < line){
                    linearLayout_Line = new LinearLayout(view.getContext());
                    linearLayout_Line.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout_Line.setGravity(Gravity.CENTER_HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLayout_Line.setLayoutParams(layoutParams);
                    background_slide.addView(linearLayout_Line);
                    testLine =line;
                    
                }




                if (linearLayout_Line != null){
                    TextQuran_textview = new TextView(view.getContext());
                    TextQuran_textview.setTextColor(Color.parseColor("#000000"));
                    TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    TextQuran_textview.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    linearLayout_Line.addView(TextQuran_textview);
                    TextQuran_textview.setTag(urlAudio.UrlAudio(aya,sura));

                }

                final String getTag_textQuran = TextQuran_textview.getTag().toString();
                /*On Long Click Listener*/
                TextQuran_textview.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        /*Get Tag Word Selected*/

                        for (int rq=0; rq<= background_slide.getChildCount();rq++){
                            /*Get Row Quran*/
                            LinearLayout rowQuran = (LinearLayout) background_slide.getChildAt(rq);
                            if (rowQuran != null){
                                for (int wq=0; wq <= rowQuran.getChildCount(); wq++){
                                    /*Get Word Quran*/
                                    TextView wordQuran = (TextView) rowQuran.getChildAt(wq);
                                    if (wordQuran != null){
                                        /*Set Background Quran*/
                                        if (wordQuran.getTag().toString().equals(getTag_textQuran)){
                                            wordQuran.setBackgroundColor(Color.parseColor("#60B3B0B0"));
                                        }
                                    }
                                }
                            }
                        }
                        return true;
                    }
                });

                TextQuran_textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tagOnclickAudio = Integer.valueOf(getTag_textQuran);
                        String testTagTextQuran = getTag_textQuran;
                        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        String getTag_textQuran ="https://dl.salamquran.com/ayat/afasy-murattal-192/"+testTagTextQuran+".mp3" ;
                        Log.d(TAG, "Link Audio: "+getTag_textQuran);
                        try {
                            mPlayer.stop();
                            mPlayer.reset();
                            mPlayer.setDataSource(getTag_textQuran);
                            mPlayer.prepare();

                        } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                        mPlayer.start();
                    }
                });
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        tagOnclickAudio++;
                        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        String getTag_textQuran ="https://dl.salamquran.com/ayat/afasy-murattal-192/"+urlAudio.UrlNextAudio(tagOnclickAudio)+".mp3" ;
                        Log.d(TAG, "Link Audio: "+getTag_textQuran);
                        try {
                            mPlayer.stop();
                            mPlayer.reset();
                            mPlayer.setDataSource(getTag_textQuran);
                            mPlayer.prepare();

                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (SecurityException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalStateException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        mPlayer.start();

                    }
                });


                switch (page){
                    case 1:
                        TextQuran_textview.setTextSize(35f);
                        TextQuran_textview.setText(Html.fromHtml(code).toString());
                        TextQuran_textview.setTypeface(font_p1);
                        break;
                    case 5:
                        TextQuran_textview.setTextSize(21f);
                        TextQuran_textview.setText(Html.fromHtml(code).toString());
                        TextQuran_textview.setTypeface(font_p5);
                        break;
                    default:
                        if (text != null){
                            TextQuran_textview.setText(" "+text+" ");
                        }else {
                            TextQuran_textview.setText(" ( "+aya+" ) ");
                        }
                        TextQuran_textview.setTextSize(15f);
                        TextQuran_textview.setTypeface(font_nabi);
                        break;
                }


            }
            pageData.close();
            mydb.close();

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView( (LinearLayout) object);
        }
    }






}
