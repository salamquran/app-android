package com.ermile.salamquran.online.fragmen.quran.tabLayout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.R;
import com.ermile.salamquran.saveData.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SureList extends Fragment {
    View fragment_sure_list;
    RecyclerView recylerview_surah;
    QuranList_adapter quranList_adapter;
    LinearLayoutManager LayoutManager;
    ArrayList<QuranList_item> quranlist;
    public SureList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_sure_list = inflater.inflate(R.layout.fragment_sure_list, container, false);
        quranlist = new ArrayList<>();


        recylerview_surah =  fragment_sure_list.findViewById(R.id.recylerview_surah);
        quranList_adapter = new QuranList_adapter(quranlist,getContext());
        LayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        getSure();
        recylerview_surah.setAdapter(quranList_adapter);
        return fragment_sure_list;



    }


    /*Get Value*/
    private void getSure(){
        try {
            String Json_text = readFromMyFile(Value.jsonFile_JuzSura);
            JSONObject json_objet  = new JSONObject(Json_text);
            JSONArray result = json_objet.getJSONArray("result");
            for (int i = 0 ; i<= result.length(); i++) {
                JSONObject juz = result.getJSONObject(i);
                int number_juz = juz.getInt("index");
                int page_juz = juz.getInt("startpage");
                quranlist.add(new QuranList_item(QuranList_item.JUZ_TYPE,0,null,
                        "Juz "+String.valueOf(number_juz),null,
                        String.valueOf(page_juz),null,0));
                recylerview_surah.setLayoutManager(LayoutManager);
                recylerview_surah.setItemAnimator(new DefaultItemAnimator());
                if (!juz.isNull("sura")){
                    String number_surah = null ;
                    String title_surah = null ;
                    String aya_surah = null ;
                    String MadeIn_surah = null ;
                    String page_surah = null ;
                    Log.i("Quran","sura not null");
                    JSONArray surah_array = juz.getJSONArray("sura");
                    for (int vers = 0 ; vers <= surah_array.length();vers++) {

                        if (!surah_array.isNull(vers)) {
                            JSONObject surah = surah_array.getJSONObject(vers);
                            number_surah = surah.getString("index");
                            title_surah = surah.getString("tname");
                            aya_surah = surah.getString("ayas");
                            MadeIn_surah = surah.getString("type");
                            page_surah = surah.getString("startpage");
                            quranlist.add(new QuranList_item(QuranList_item.SURAH_TYPE, 0,
                                    number_surah,
                                    title_surah,
                                    MadeIn_surah + " - " + aya_surah + " aya",
                                    page_surah, null, 0));
                            recylerview_surah.setLayoutManager(LayoutManager);
                            recylerview_surah.setItemAnimator(new DefaultItemAnimator());
                        }
                        Log.i("Quran", "number_surah= " + number_surah);
                    }
                }
            }

            } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readFromMyFile(String filename) throws IOException {
        File file = new File(getContext().getFilesDir(), filename+".json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        return text.toString();
    }

}
