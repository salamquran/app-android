package com.ermile.salamquran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import com.duolingo.open.rtlviewpager.RtlViewPager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QuranPage extends AppCompatActivity {
    private static String TAG = "QuranPage";

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



    private String readFromMyFile(String filename) throws IOException {
        File file = new File(getApplicationContext().getFilesDir(), filename+".json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        return text.toString();
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
            linequran_1 = view.findViewById(R.id.linequran_1);
            linequran_2 = view.findViewById(R.id.linequran_2);
            linequran_3 = view.findViewById(R.id.linequran_3);
            linequran_4 = view.findViewById(R.id.linequran_4);
            linequran_5 = view.findViewById(R.id.linequran_5);
            linequran_6 = view.findViewById(R.id.linequran_6);
            linequran_7 = view.findViewById(R.id.linequran_7);
            linequran_8 = view.findViewById(R.id.linequran_8);
            linequran_9 = view.findViewById(R.id.linequran_9);
            linequran_10 = view.findViewById(R.id.linequran_10);
            linequran_11 = view.findViewById(R.id.linequran_11);
            linequran_12 = view.findViewById(R.id.linequran_12);
            linequran_13 = view.findViewById(R.id.linequran_13);
            linequran_14 = view.findViewById(R.id.linequran_14);
            linequran_15 = view.findViewById(R.id.linequran_15);


            Typeface font_nabi=ResourcesCompat.getFont(context, R.font.font_nabi);
            Typeface font_p1 = ResourcesCompat.getFont(context, R.font.p1);
            Typeface font_p5 = ResourcesCompat.getFont(context, R.font.p5);
            Typeface font_bismellah =ResourcesCompat.getFont(context, R.font.bismillah);


            SQLiteDatabase mydb = new MyDatabase(QuranPage.this).getWritableDatabase();
            Cursor pageData = mydb.rawQuery("SELECT * FROM quran_word WHERE page="+position, null);

            while (pageData.moveToNext()) {
                String text = pageData.getString(pageData.getColumnIndex("text"));
                String code = pageData.getString(pageData.getColumnIndex("code"));
                final int aya = pageData.getInt(pageData.getColumnIndex("aya"));
                final int sura = pageData.getInt(pageData.getColumnIndex("sura"));
                int line = pageData.getInt(pageData.getColumnIndex("line"));
                int page = pageData.getInt(pageData.getColumnIndex("page"));
                int positions = pageData.getInt(pageData.getColumnIndex("position"));
                String audio = pageData.getString(pageData.getColumnIndex("audio"));

                final TextView TextQuran_textview = new TextView(view.getContext());
                TextQuran_textview.setTextColor(Color.parseColor("#000000"));
                TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TextQuran_textview.setTag(aya);

                /*On Long Click Listener*/
                TextQuran_textview.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        /*Get Tag Word Selected*/
                        String getTag_textQuran = TextQuran_textview.getTag().toString();
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

                        String audioUrl ="https://dl.salamquran.com/ayat/afasy-murattal-192/"+ urlAudio.UrlAudio(aya,sura)+".mp3";
                        Log.d(TAG, ""+audioUrl);

                        // Initialize a new media player instance
                        final MediaPlayer mPlayer = new MediaPlayer();

                        // Set the media player audio stream type
                        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        //Try to play music/audio from url
                        try{
                            // Set the audio data source
                            mPlayer.setDataSource(audioUrl);
                            // Prepare the media player
                            mPlayer.prepare();

                            // Start playing audio from http url
                            mPlayer.start();

                            // Inform user for audio streaming
                            Toast.makeText(getApplicationContext(),"Playing",Toast.LENGTH_SHORT).show();
                        }catch (IOException e){
                            // Catch the exception
                            e.printStackTrace();
                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                        }catch (SecurityException e){
                            e.printStackTrace();
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }

                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(getApplicationContext(),"End",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


                switch (page){
                    case 1:
                        TextQuran_textview.setTextSize(28f);
                        TextQuran_textview.setText(Html.fromHtml(code).toString());
                        TextQuran_textview.setTypeface(font_p1);
                        break;
                    case 5:
                        TextQuran_textview.setTextSize(20f);
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

                switch (line){
                    case 1:
                        linequran_1.addView(TextQuran_textview);
                        break;
                    case 2:
                        linequran_2.addView(TextQuran_textview);
                        break;
                    case 3:
                        linequran_3.addView(TextQuran_textview);
                        break;
                    case 4:
                        linequran_4.addView(TextQuran_textview);
                        break;
                    case 5:
                        linequran_5.addView(TextQuran_textview);
                        break;
                    case 6:
                        linequran_6.addView(TextQuran_textview);
                        break;
                    case 7:
                        linequran_7.addView(TextQuran_textview);
                        break;
                    case 8:
                        linequran_8.addView(TextQuran_textview);
                        break;
                    case 9:
                        linequran_9.addView(TextQuran_textview);
                        break;
                    case 10:
                        linequran_10.addView(TextQuran_textview);
                        break;
                    case 11:
                        linequran_11.addView(TextQuran_textview);
                        break;
                    case 12:
                        linequran_12.addView(TextQuran_textview);
                        break;
                    case 13:
                        linequran_13.addView(TextQuran_textview);
                        break;
                    case 14:
                        linequran_14.addView(TextQuran_textview);
                        break;
                    case 15:
                        linequran_15.addView(TextQuran_textview);
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
