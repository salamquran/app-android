package com.ermile.salamquran.Fragmnet.QuranList;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.Adaptor.QuranListAdaptor;
import com.ermile.salamquran.Function.Utility.ReadFile;
import com.ermile.salamquran.Item.item_QuranList;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SureList extends Fragment {
    View fragment_sure_list;
    RecyclerView recylerview_surah;
    QuranListAdaptor quranList_adapter;
    LinearLayoutManager LayoutManager;
    ArrayList<item_QuranList> quranlist;

    public SureList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment_sure_list = inflater.inflate(R.layout.fragment_sure_list, container, false);
        quranlist = new ArrayList<>();


        recylerview_surah =  fragment_sure_list.findViewById(R.id.recylerview_surah);
        quranList_adapter = new QuranListAdaptor(quranlist,getContext());
        LayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        getSure();
        recylerview_surah.setAdapter(quranList_adapter);
        return fragment_sure_list;
    }


    /*Get Value*/
    private void getSure(){
        try {
            String Json_text = ReadFile.FromStorage(getContext(), file.list_sure, format.json);
            JSONObject json_objet  = new JSONObject(Json_text);
            JSONArray result = json_objet.getJSONArray("result");
            for (int i = 0 ; i<= result.length(); i++) {
                JSONObject juz = result.getJSONObject(i);
                int number_juz = juz.getInt("index");
                int page_juz = juz.getInt("startpage");
                quranlist.add(new item_QuranList(item_QuranList.JUZ_TYPE,0,null,
                        getString(R.string.juz)+String.valueOf(number_juz),null,
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

                        String type = null;
                        if (!surah_array.isNull(vers)) {
                            JSONObject surah = surah_array.getJSONObject(vers);
                            number_surah = surah.getString("index");
                            title_surah = surah.getString("tname");
                            aya_surah = surah.getString("ayas");
                            MadeIn_surah = surah.getString("type");

                            switch (MadeIn_surah){
                                case "medinan":
                                    type = "مدینه" ;
                                    break;
                                case "meccan":
                                    type = "مکه" ;
                                    break;
                            }

                            page_surah = surah.getString("startpage");
                            quranlist.add(new item_QuranList(item_QuranList.SURAH_TYPE, 0,
                                    number_surah,
                                    title_surah,
                                    type + " - " + aya_surah + getString(R.string.aya),
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


}
