package com.ermile.salamquran.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ermile.salamquran.Adaptors.Adaptor_ListQuran;
import com.ermile.salamquran.Functions.FileManager;
import com.ermile.salamquran.ItemModels.item_ListQuran;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Value.file;
import com.ermile.salamquran.Value.format;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListQuran_Detail extends Fragment {

    private RecyclerView recylerview;
    private LinearLayoutManager LayoutManager;
    private ArrayList<item_ListQuran> quranlist;
    private int HIZB = 0;
    private boolean sure = false;

    public ListQuran_Detail(boolean isSura) {
        // Required empty public constructor
        sure = isSura;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_juz_list = inflater.inflate(R.layout.fragment_list_quran_detail, container, false);

        quranlist= new ArrayList<>();
        recylerview = fragment_juz_list.findViewById(R.id.recylerview_juz);
        Adaptor_ListQuran quranList_adapter = new Adaptor_ListQuran(quranlist, getContext());
        LayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        if (sure){
            getSure();
        }
        else {
            getHezb();
        }
        recylerview.setAdapter(quranList_adapter);
        return fragment_juz_list;
    }

    /*Get Value*/
    private void getSure(){
        try {
            String Json_text = FileManager.read_FromStorage(Objects.requireNonNull(getContext()), file.list_sure, format.json);
            JSONObject json_objet  = new JSONObject(Json_text);
            JSONArray result = json_objet.getJSONArray("result");
            for (int i = 0 ; i<= result.length(); i++) {
                JSONObject juz = result.getJSONObject(i);
                int number_juz = juz.getInt("index");
                int page_juz = juz.getInt("startpage");
                quranlist.add(new item_ListQuran(item_ListQuran.JUZ_TYPE,0,null,
                        getString(R.string.juz)+String.valueOf(number_juz),null,
                        String.valueOf(page_juz),null,0));
                recylerview.setLayoutManager(LayoutManager);
                recylerview.setItemAnimator(new DefaultItemAnimator());
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
                            quranlist.add(new item_ListQuran(item_ListQuran.SURAH_TYPE, 0,
                                    number_surah,
                                    title_surah,
                                    type + " - " + aya_surah + getString(R.string.aya),
                                    page_surah, null, 0));
                            recylerview.setLayoutManager(LayoutManager);
                            recylerview.setItemAnimator(new DefaultItemAnimator());
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

    private void getHezb(){
        try {
            String Json_text = FileManager.read_FromStorage(Objects.requireNonNull(getContext()), file.list_juz, format.json);
            JSONObject json_objet  = new JSONObject(Json_text);
            JSONObject result = json_objet.getJSONObject("result");

            String number_hezb;
            String title_hezb = null;
            String page_hezb = null;
            String aya_hezb = null;
            String sureh_hezb = null;
            int bgHezb_hezb = 0;

            for(Iterator<String> key_result = result.keys(); key_result.hasNext();) {
                String get_keyResult = key_result.next();
                JSONObject juz = result.getJSONObject(get_keyResult);
                quranlist.add(new item_ListQuran(item_ListQuran.JUZ_TYPE,0,null,
                        getString(R.string.juz)+get_keyResult,null,
                        "",null,0));
                recylerview.setLayoutManager(LayoutManager);
                recylerview.setItemAnimator(new DefaultItemAnimator());

                for(Iterator<String> key_juz = juz.keys();key_juz.hasNext();) {
                    String get_keyhezb = key_juz.next();
                    JSONObject hezb = juz.getJSONObject(get_keyhezb);
                    HIZB++;

                    for(Iterator<String> key_rubHezb = hezb.keys();key_rubHezb.hasNext();) {
                        String get_keyrubHezb = key_rubHezb.next();
                        JSONObject rubHezb = hezb.getJSONObject(get_keyrubHezb);


                        if (!rubHezb.isNull("sura_detail")){
                            JSONObject surehHezb = rubHezb.getJSONObject("sura_detail");
                            if (!surehHezb.isNull("tname")){
                                sureh_hezb = surehHezb.getString("tname");
                            }
                        }

                        if (rubHezb.getInt("index_rub") == 1){
                            number_hezb = String.valueOf(HIZB);
                        }else {
                            number_hezb = "";
                        }

                        int getHezb = rubHezb.getInt("index_rub");

                        switch (getHezb){
                            case 1:
                                bgHezb_hezb = R.drawable.hezb_1;
                                break;
                            case 2:
                                bgHezb_hezb = R.drawable.hezb_2;
                                break;
                            case 3:
                                bgHezb_hezb = R.drawable.hezb_3;
                                break;
                            case 4:
                                bgHezb_hezb = R.drawable.hezb_4;
                                break;
                        }
                        if (!rubHezb.isNull("first_word")){
                            title_hezb = rubHezb.getString("first_word");
                        }
                        if (!rubHezb.isNull("page")){
                            page_hezb = rubHezb.getString("page");
                        }
                        if (!rubHezb.isNull("aya")){
                            aya_hezb = rubHezb.getString("aya");
                        }

                        quranlist.add(new item_ListQuran(item_ListQuran.HEZB_TYPE,0,
                                null,
                                title_hezb,
                                getString(R.string.surah)+sureh_hezb+ ", "+getString(R.string.aya)+aya_hezb,
                                page_hezb,
                                number_hezb,
                                bgHezb_hezb));

                        recylerview.setLayoutManager(LayoutManager);
                        recylerview.setItemAnimator(new DefaultItemAnimator());

                    }

                }


            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
