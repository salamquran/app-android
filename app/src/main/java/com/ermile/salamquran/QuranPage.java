package com.ermile.salamquran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.ermile.salamquran.network.AppContoroler;
import com.ermile.salamquran.saveData.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuranPage extends AppCompatActivity {


    String lineType_detail = null;
    String nameSurah_json = null;
    String page = "1";

    public Handler mHandler;
    public boolean continue_or_stop;

    ViewpagersAdapter PagerAdapter;  // for View page
    RtlViewPager viewpager; //  for dots & Button in XML
    private TextView number_pageQuran , number_juzQuran,title_surahQuran;
    public int count = 605; // Slide number


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
                number_pageQuran.setText(String.valueOf(position));
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
                }
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





            final Typeface font_nabi= ResourcesCompat.getFont(context, R.font.font_nabi);
            final Typeface font_bismellah =ResourcesCompat.getFont(context, R.font.bismillah);
            page = "https://salamquran.com/fa/api/v6/page/wbw?index="+String.valueOf(position);

            final ArrayList<String> TAG_WBW = new ArrayList<String>();


            try {

                String jsonText = readFromMyFile(Value.jsonFile_QuranWBW);
                JSONArray jsonObject = new JSONArray(jsonText);


                final LinearLayout linearLayout_wordQuran = new LinearLayout(view.getContext());
                linearLayout_wordQuran.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER_HORIZONTAL));
                linearLayout_wordQuran.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                linearLayout_wordQuran.setOrientation(LinearLayout.HORIZONTAL);
                ViewCompat.setLayoutDirection(linearLayout_wordQuran,
                        ViewCompat.LAYOUT_DIRECTION_RTL);
                background_slide.addView(linearLayout_wordQuran);

                /*Get Word's of Quran */
                for (int w = 0; w <= jsonObject.length(); w++) {
                    JSONObject wordQuran = jsonObject.getJSONObject(w);
                    String page = wordQuran.getString("page");
                    if (position == Integer.valueOf(page)){
                        final TextView TextQuran_textview = new TextView(view.getContext());
                        TextQuran_textview.setTextSize(12.1f);
                        TextQuran_textview.setTextColor(Color.parseColor("#000000"));
                        TextQuran_textview.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        TextQuran_textview.setTypeface(font_nabi);
                        linearLayout_wordQuran.addView(TextQuran_textview);


                        JSONObject object_wordQuran_JsonArray = jsonObject.getJSONObject(w);
                        String textQuran_json = " " + object_wordQuran_JsonArray.getString("text") + " ";
                        String numberAya_json = " (" + object_wordQuran_JsonArray.getString("aya") + ") ";
                        if (textQuran_json.equals(" null ")) {
                            TextQuran_textview.setText(numberAya_json);
                        } else {
                            TextQuran_textview.setText(textQuran_json);

                        }
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView( (LinearLayout) object);
        }
    }






}
